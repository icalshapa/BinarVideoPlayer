package com.binarteamtwo.binarvideoplayer.ui.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.databinding.ActivityPlayerBinding
import com.binarteamtwo.binarvideoplayer.data.local.room.MediaPlaylistRoomDatabase
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.google.android.material.snackbar.Snackbar
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.util.regex.Matcher
import java.util.regex.Pattern

class PlayerActivity : AppCompatActivity(), PlayerContract.View {
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var presenter: PlayerContract.Presenter
    private var videoId: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun getIntentData() {
        videoId = intent?.getIntExtra(Constant.EXTRAS_DATA_VIDEO, -1)
    }

    private fun parseYoutubeUrl(url: String): String? {
        val pattern =
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(url)
        return if (matcher.find()) {
            matcher.group()
        } else {
            null
        }
    }

    private fun bindData(mediaPlaylist: MediaPlaylist?) {
        supportActionBar?.hide()
        val youtubeId = mediaPlaylist?.videoUrl?.let { parseYoutubeUrl(it) }
        binding.tvPlayerTitle.text = mediaPlaylist?.title
        binding.tvPlayerArtist.text = mediaPlaylist?.singer
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubeId?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        })
        setFabFavoriteIcon(mediaPlaylist)
        binding.fabFavorite.setOnClickListener {
            mediaPlaylist?.let {
                presenter.changeStatusFavorite(it)
            }
        }
        binding.fabEdit.setOnClickListener {
            //Add New Song
        }

    }

    private fun setFabFavoriteIcon(mediaPlaylist: MediaPlaylist?) {
        binding.fabFavorite.setImageResource(if (mediaPlaylist?.isFavorite == true) R.drawable.ic_btn_favorited_true else R.drawable.ic_btn_favorited_false)
    }

    override fun onFetchVideoSuccess(mediaPlaylist: MediaPlaylist) {
        bindVideoData(mediaPlaylist)
    }

    override fun onFetchVideoFailed() {
        Toast.makeText(this, "Failed fetching video $videoId", Toast.LENGTH_SHORT).show()
    }

    override fun onChangeFavoriteStatusSuccess(mediaPlaylist: MediaPlaylist) {
        bindVideoData(mediaPlaylist)
        if (mediaPlaylist.isFavorite) {
            Snackbar.make(binding.root, "Video Favorited", Snackbar.LENGTH_SHORT)
                .show()
        }else{
            Snackbar.make(binding.root, "Video Unfavorited", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onChangeFavoriteStatusFailed() {
        Toast.makeText(this, "Failed to change favorite status", Toast.LENGTH_SHORT).show()
    }

    override fun bindVideoData(mediaPlaylist: MediaPlaylist?) {
        bindData(mediaPlaylist)
    }

    override fun getData() {
        videoId?.let { presenter.getVideo(it) }
    }

    override fun initView() {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntentData()
        val dataSource = MediaPlaylistDataSource(MediaPlaylistRoomDatabase.getInstance(this).mediaPlaylistDao())
        presenter = PlayerPresenter(dataSource,this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}