package com.binarteamtwo.binarvideoplayer.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.ActivityLoginBinding
import com.binarteamtwo.binarvideoplayer.ui.splash.startAnimation
import com.binarteamtwo.binarvideoplayer.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        onClick()

        val animation = AnimationUtils.loadAnimation(this, R.anim.logo_anim).apply {
            interpolator = AccelerateDecelerateInterpolator()
        }
        binding.ivLogoLogin.startAnimation(animation) {}

    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
