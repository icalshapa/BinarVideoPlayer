package com.binarteamtwo.binarvideoplayer.ui.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}