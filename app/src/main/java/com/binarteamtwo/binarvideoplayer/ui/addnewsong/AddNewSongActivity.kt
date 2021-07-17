package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.binarteamtwo.binarvideoplayer.data.local.room.MediaPlaylistRoomDatabase
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.databinding.ActivityAddNewSongBinding
import com.binarteamtwo.binarvideoplayer.databinding.FragmentAddDialogBinding
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import com.binarteamtwo.binarvideoplayer.ui.main.MainActivity

class AddNewSongActivity : AppCompatActivity(), AddNewSongContract.View {
    private lateinit var binding: ActivityAddNewSongBinding
    private lateinit var presenter: AddNewSongContract.Presenter
    private var appMode: Int = MODE_INSERT
    private var playlist: MediaPlaylist? = null

    companion object {
        const val MODE_INSERT = 0
        const val MODE_EDIT = 1
        const val ARG_MODE = "ARG_MODE"
        const val ARG_PLAYLIST_DATA = "ARG_PLAYLIST_DATA"

        fun startActivity(context: Context, appMode: Int, playlist: MediaPlaylist?) {
            val intent = Intent(context, AddNewSongActivity::class.java)
            intent.putExtra(ARG_MODE, appMode)
            playlist.let {
                intent.putExtra(ARG_PLAYLIST_DATA, playlist)
            }
            context.startActivity(intent)
        }

        fun startActivity(context: Context, appMode: Int) {
            startActivity(context, appMode, null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun addPlaylist() {
        binding.btnAddSong.setOnClickListener {
            savePlaylist()
        }
    }

    private fun savePlaylist() {
        if (isPlaylistFilled()) {
            if (appMode == MODE_EDIT) {
                //editing playlist
                playlist = playlist?.copy()?.apply {
                    title = binding.etTitleSong.text.toString()
                    singer = binding.etSingerName.text.toString()
                    imgIconUrl = binding.etIconUrl.text.toString()
                    videoUrl = binding.etVideoUrl.text.toString()
                }
                playlist?.let { presenter.updatePlaylist(it) }
            } else {
                //insert playlist
                playlist = MediaPlaylist(
                    title = binding.etTitleSong.text.toString(),
                    singer = binding.etSingerName.text.toString(),
                    imgIconUrl = binding.etIconUrl.text.toString(),
                    videoUrl = binding.etVideoUrl.text.toString()
                )
                playlist?.let { presenter.updatePlaylist(it) }
            }
        }
    }

    private fun isPlaylistFilled(): Boolean {
        val title = binding.etTitleSong.text.toString()
        val singer = binding.etSingerName.text.toString()
        val imgIconUrl = binding.etIconUrl.text.toString()
        val videoUrl = binding.etVideoUrl.text.toString()
        var isFormValid = true

        if (title.isEmpty()) {
            isFormValid = false
            binding.tilTitleSong.isErrorEnabled = true
            binding.tilTitleSong.error = "Video Title must be filled"
        } else {
            binding.tilTitleSong.isErrorEnabled = false
        }

        if (singer.isEmpty()) {
            isFormValid = false
            binding.tilSingerName.isErrorEnabled = true
            binding.tilSingerName.error = "Singer Name must be filled"
        } else {
            binding.tilSingerName.isErrorEnabled = false
        }

        if (imgIconUrl.isEmpty()) {
            isFormValid = false
            binding.tilIconUrl.isErrorEnabled = true
            binding.tilIconUrl.error = "Icon URL must be filled"
        } else {
            binding.tilIconUrl.isErrorEnabled = false
        }

        if (videoUrl.isEmpty()) {
            isFormValid = false
            binding.tilVideoUrl.isErrorEnabled = true
            binding.tilVideoUrl.error = "Video URL must be filled"
        } else {
            binding.tilVideoUrl.isErrorEnabled = false
        }
        return isFormValid
    }

    override fun onSuccess() {
        // save playlist success
        addDialog()
    }

    override fun onFailed() {
        // save playlist failed
        Toast.makeText(this, "Saving Playlist Failed!, Please Try Again", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun getIntentData() {
        appMode = intent.getIntExtra(ARG_MODE, 0)
        playlist = intent.getParcelableExtra(ARG_PLAYLIST_DATA)
    }

    override fun initializePlaylist() {
        //initialize presenter
        val dataSource =
            MediaPlaylistDataSource(MediaPlaylistRoomDatabase.getInstance(this).mediaPlaylistDao())
        presenter = AddNewSongPresenter(dataSource, this)
        //preset data when form mode is edit mode
        if (appMode == MODE_EDIT) {
            playlist?.let {
                binding.etTitleSong.setText(it.title)
                binding.etSingerName.setText(it.singer)
                binding.etIconUrl.setText(it.imgIconUrl)
                binding.etVideoUrl.setText(it.videoUrl)
            }
            //"Edit Data"
            supportActionBar?.title = "Edit Playlist"
        } else {
            supportActionBar?.title = "Add Playlist"
        }
    }

    override fun initView() {
        binding = ActivityAddNewSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addPlaylist()
        getIntentData()
        initializePlaylist()
    }

    private fun addDialog() {
        val builder = AlertDialog.Builder(this)
        val binding = FragmentAddDialogBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        binding.tvDialogYes.setOnClickListener {
            //add song to playlist
            playlist.let {
                it?.let { it1 -> presenter.insertPlaylist(it1) }
                Toast.makeText(this, "Save song to playlist Success!", Toast.LENGTH_SHORT).show()
                finish()
            }
            //back to main menu?
        }
        binding.tvDialogNo.setOnClickListener {
            //back to main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Back To Homepage", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}