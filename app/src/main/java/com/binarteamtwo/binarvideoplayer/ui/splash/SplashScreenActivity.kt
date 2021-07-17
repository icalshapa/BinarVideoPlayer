package com.binarteamtwo.binarvideoplayer.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.ActivitySplashScreenBinding
import com.binarteamtwo.binarvideoplayer.startAnimation
import com.binarteamtwo.binarvideoplayer.ui.login.LoginActivity
import com.binarteamtwo.binarvideoplayer.ui.main.MainActivity
import kotlin.math.log

class SplashScreenActivity : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private lateinit var binding: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        setEventSplash()

        val animation = AnimationUtils.loadAnimation(this, R.anim.logo_anim).apply {
            interpolator = AccelerateDecelerateInterpolator()
        }
        binding.ivLogoSplash.startAnimation(animation) {}


    }


    override fun onDestroy() {
        super.onDestroy()
        if (timer != null) {
            timer?.cancel()
            timer = null

        }


    }


    private fun setEventSplash() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
        timer?.start()

    }

}