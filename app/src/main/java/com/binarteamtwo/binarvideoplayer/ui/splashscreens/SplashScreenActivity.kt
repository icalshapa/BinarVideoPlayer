package com.binarteamtwo.binarvideoplayer.ui.splashscreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.local.sharedpreference.SessionPreferences
import com.binarteamtwo.binarvideoplayer.data.network.datasource.BinarDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.services.BinarApiServices
import com.binarteamtwo.binarvideoplayer.databinding.ActivitySplashScreenBinding
import com.binarteamtwo.binarvideoplayer.ui.intro.IntroActivity
import com.binarteamtwo.binarvideoplayer.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity(), SplashScreenContract.BaseView {
    private val TAG = SplashScreenActivity::class.java.simpleName
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var viewModel: SplashScreenViewModel
    private lateinit var sessionPreference: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        initView()
        startAnimLogo()
        startAnimText()


    }

    fun startAnimLogo() {
        val imageSplash = binding.ivLogoSplash
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_anim)
        imageSplash.startAnimation(sideAnimation)
    }

    fun startAnimText() {
        val textSplash = binding.ivLogoSplashTwo
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.text_anim)
        textSplash.startAnimation(sideAnimation)


    }

    override fun navigateToIntro() {
        val intent = Intent(this, IntroActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


    override fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun checkLogin() {
        if (sessionPreference.authToken != null) {
            viewModel.getSyncData()
        } else {
            //navigate to login, if no token
            lifecycleScope.launch(Dispatchers.IO) {
                delay(1000)
                lifecycleScope.launch(Dispatchers.Main) {
                    navigateToIntro()
                }
            }
        }
    }

    override fun deleteSessionIntroAndLogin() {
        sessionPreference.deleteSession()
    }

    override fun initView() {
        initViewModel()
        checkLogin()
    }

    override fun initViewModel() {
        sessionPreference = SessionPreferences(this)
        val apiService = BinarApiServices.getInstance(sessionPreference)
        apiService?.let {
            val dataSource = BinarDataSource(it)
            val repository = SplashScreenRepository(dataSource)
            viewModel = GenericViewModelFactory(SplashScreenViewModel(repository))
                .create(SplashScreenViewModel::class.java)
        }
        viewModel.syncData.observe(this, { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "initViewModel:Splash Loading")
                }
                is Resource.Success -> {
                    navigateToHome()
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Session Expired", Toast.LENGTH_SHORT).show()
                    deleteSessionIntroAndLogin()
                    navigateToIntro()
                }
            }
        })

    }
}