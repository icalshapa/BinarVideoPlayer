package com.binarteamtwo.binarvideoplayer.addnewsong

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.ActivityAddNewSongBinding
import com.binarteamtwo.binarvideoplayer.databinding.FragmentAddDialogBinding

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
            } playlist = playlist. copy ()?.apply {
                title = binding.etTitleSong.text.toString()
                singer = binding.etSingerName.text.toString()
                imgIconUrl = binding.etIconUrl.text.toString()
            }
            playlist?.let { presenter.updatePlaylist(it) }
        } else {
            //insert playlist
            playlist = MediaStore.Audio.Playlists(
                title = binding.etTitleSong.text.toString(),
                singer = binding.etSingerName.text.toString(),
                imgIconUrl = binding.etIconUrl.text.toString()
            )
            playlist?.let { presenter.updatePlaylist(it) }
        }

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
        val dataSource = MediaPlaylistDataSource(MediaPlaylistRoomDatabase.getInstance(this).todoDao())
        presenter = AddNewSongPresenter(dataSource, this)
        //preset data when form mode is edit mode
        if (appMode == MODE_EDIT) {
            playlist.let {
                binding.etTitleSong.setText(it.title)
                binding.etSingerName.setText(it.desc)
                binding.etIconUrl.setText(it.imgHeaderUrl)
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
    }

    private fun addDialog(){
        val builder = AlertDialog.Builder(this)
        val binding = FragmentAddDialogBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        binding.tvDialogYes.setOnClickListener {
            //add song to playlist
            playlist.let { presenter.insertPlaylist(it)
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