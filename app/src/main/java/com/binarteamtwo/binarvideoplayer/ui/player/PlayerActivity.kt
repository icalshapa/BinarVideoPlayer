package com.binarteamtwo.binarvideoplayer.ui.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.databinding.ActivityPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var videoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initView()

    }

    private fun initView() {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntentData()
        bindData()


    }

    private fun getIntentData() {
        videoId = intent?.getStringExtra(Constant.EXTRAS_DATA_VIDEO)

    }

    private fun bindData() {
        supportActionBar?.hide()
                binding.youtubePlayerView.addYouTubePlayerListener(
            object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    videoId?.let { youTubePlayer.loadVideo(it, 0f) }
                }

            })


    }

}






