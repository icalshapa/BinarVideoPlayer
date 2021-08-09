package com.binarteamtwo.binarvideoplayer.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.local.sharedpreference.SessionPreferences
import com.binarteamtwo.binarvideoplayer.data.network.datasource.BinarDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification.RegisterRequest
import com.binarteamtwo.binarvideoplayer.data.network.entity.services.BinarApiServices
import com.binarteamtwo.binarvideoplayer.databinding.ActivityRegisterBinding
import com.binarteamtwo.binarvideoplayer.ui.login.LoginActivity
import com.binarteamtwo.binarvideoplayer.utils.StringUtils

class RegisterActivity : AppCompatActivity(), RegisterContract.BaseView {
    private val TAG = RegisterActivity::class.java.simpleName
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var sessionPreferences: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        initView()
    }

    override fun setToolbar() {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setLoadingState(isLoadingVisible: Boolean) {
        binding.pbLoading.visibility = if (isLoadingVisible) View.VISIBLE else View.GONE
    }

    override fun checkFormValidation(): Boolean {
        val email = binding.etEmail.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        //for checking is email empty
        when {
            email.isEmpty() -> {
                isFormValid = false
                binding.tilEmail.isErrorEnabled = true
                binding.tilEmail.error = getString(R.string.error_email_empty)
            }
            StringUtils.isEmailValid(email).not() -> {
                isFormValid = false
                binding.tilEmail.isErrorEnabled = true
                binding.tilEmail.error = getString(R.string.error_email_invalid)
            }
            else -> {
                binding.tilEmail.isErrorEnabled = false
            }
        }
        //for checking is Password empty
        if (password.isEmpty()) {
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.error_password_empty)
        } else {
            binding.tilPassword.isErrorEnabled = false
        }
        //for checking is Password empty
        if (username.isEmpty()) {
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.error_username_empty)
        } else {
            binding.tilUsername.isErrorEnabled = false
        }
        return isFormValid
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun initView() {
        initViewModel()
        setToolbar()
        setOnClick()
    }

    override fun initViewModel() {
        sessionPreferences = SessionPreferences(this)
        val apiService = BinarApiServices.getInstance(sessionPreferences)
        apiService?.let {
            val dataSource = BinarDataSource(it)
            val repository = RegisterRepository(dataSource)
            viewModel = GenericViewModelFactory(RegisterViewModel(repository))
                .create(RegisterViewModel::class.java)
        }
        viewModel.registerResponse.observe(this, { response ->
            when (response) {
                is Resource.Loading -> {
                    setLoadingState(true)
                }
                is Resource.Success -> {
                    setLoadingState(false)
                    Toast.makeText(this, R.string.text_register_success, Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
                is Resource.Error -> {
                    setLoadingState(false)
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
            }
        })
    }

    override fun setOnClick() {
        binding.btnRegister.setOnClickListener {
            if(checkFormValidation()){
                viewModel.registerUser(
                    RegisterRequest(
                        email = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString(),
                        username = binding.etUsername.text.toString()
                    )
                )
            }
        }
    }
}