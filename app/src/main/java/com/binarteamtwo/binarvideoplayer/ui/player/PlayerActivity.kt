package com.binarteamtwo.binarvideoplayer.ui.player

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.local.room.MediaPlaylistRoomDatabase
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import com.binarteamtwo.binarvideoplayer.databinding.ActivityPlayerBinding
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.util.regex.Matcher
import java.util.regex.Pattern

class PlayerActivity : AppCompatActivity()/*, PlayerContract.View*/ {
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var viewModel: PlayerViewModel
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

    private fun bindData(moviePlaylist: MoviePlaylist?) {
        supportActionBar?.hide()
       /* val youtubeId = moviePlaylist?.videoUrl?.let { parseYoutubeUrl(it) }
        binding.tvPlayerTitle.text = moviePlaylist?.title
        binding.tvPlayerArtist.text = moviePlaylist?.singer
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubeId?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        })
        setFabFavoriteIcon(moviePlaylist)
        binding.fabFavorite.setOnClickListener {
            moviePlaylist?.let {
                viewModel.changeStatusFavorite(it)
            }
        }
        binding.fabEdit.setOnClickListener {
            AddNewSongActivity.startActivity(this,AddNewSongActivity.MODE_EDIT,moviePlaylist)
        }*/

    }

    private fun setFabFavoriteIcon(moviePlaylist: MoviePlaylist?) {
        binding.fabFavorite.setImageResource(if (moviePlaylist?.isFavorite == true) R.drawable.ic_btn_favorited_true else R.drawable.ic_btn_favorited_false)
    }

    /*override fun onFetchVideoSuccess(moviePlaylist: MoviePlaylist) {
        bindVideoData(moviePlaylist)
    }

    override fun onFetchVideoFailed() {
        Toast.makeText(this, "Failed fetching video $videoId", Toast.LENGTH_SHORT).show()
    }

    override fun onChangeFavoriteStatusSuccess(moviePlaylist: MoviePlaylist) {
        getData()
        if (moviePlaylist.isFavorite) {
            Snackbar.make(binding.root, getString(R.string.player_snackbar_favorite_true), Snackbar.LENGTH_SHORT)
                .show()
        }else{
            Snackbar.make(binding.root, getString(R.string.player_snackbar_favorite_false), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onChangeFavoriteStatusFailed() {
        Toast.makeText(this, getString(R.string.player_toast_favorite_failed), Toast.LENGTH_SHORT).show()
    }

    override fun bindVideoData(moviePlaylist: MoviePlaylist?) {
        bindData(moviePlaylist)
    }

    override fun getData() {
        videoId?.let { viewModel.getVideo(it) }
    }*/

    fun initView() {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntentData()
    /*    initViewModel()*/
    }

    /*override fun initViewModel() {
        val dataSource = MovieDataSource(MediaPlaylistRoomDatabase.getInstance(this).mediaPlaylistDao())
        val repository = PlayerRepository(dataSource)
        viewModel = GenericViewModelFactory(PlayerViewModel(repository)).create(
            PlayerViewModel::class.java
        )
        viewModel.playerData.observe(this,{
            when (it){
                is Resource.Loading ->{

                }
                is Resource.Success -> {
                    it.data?.let { data ->
                        onFetchVideoSuccess(data)
                    }
                }
                is Resource.Error ->{
                    onFetchVideoFailed()
                }
            }
        })
        viewModel.changeStatusResult.observe(this,{
            if(it.first){
                it.second?.let { video ->
                    onChangeFavoriteStatusSuccess(video)
                }
            }else{
                onChangeFavoriteStatusFailed()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getData()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }*/
}