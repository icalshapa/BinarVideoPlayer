package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.data.local.room.MediaPlaylistRoomDatabase
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.databinding.ActivityAddNewSongBinding
import com.binarteamtwo.binarvideoplayer.databinding.FragmentAddDialogBinding
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist

// class AddNewSongActivity : AppCompatActivity(), AddNewSongContract.View {
// private lateinit var binding: ActivityAddNewSongBinding
// private lateinit var viewModel: AddNewSongViewModel
// private var appMode: Int = MODE_INSERT
// private var playlist: MoviePlaylist? = null
//
// companion object {
// const val MODE_INSERT = 0
// const val MODE_EDIT = 1
// const val ARG_MODE = "ARG_MODE"
// const val ARG_PLAYLIST_DATA = "ARG_PLAYLIST_DATA"
//
// fun startActivity(context: Context, appMode: Int, playlist: MoviePlaylist?) {
// val intent = Intent(context, AddNewSongActivity::class.java)
// intent.putExtra(ARG_MODE, appMode)
// playlist.let {
// intent.putExtra(ARG_PLAYLIST_DATA, playlist)
// }
// context.startActivity(intent)
// }
//
// fun startActivity(context: Context, appMode: Int) {
// startActivity(context, appMode, null)
// }
// }
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
//
// initView()
// }
//
// private fun addPlaylist() {
// binding.btnAddSong.setOnClickListener {
// if (isPlaylistFilled()){
// addDialog()
// }
// }
// }
//
// private fun savePlaylist() {
//
// if (isPlaylistFilled()) {
// if (appMode == MODE_EDIT) {
// //editing playlist
// playlist = playlist?.copy()?.apply {
// title = binding.etTitleSong.text.toString()
// singer = binding.etSingerName.text.toString()
// imgIconUrl = binding.etIconUrl.text.toString()
// videoUrl = binding.etVideoUrl.text.toString()
//
// }
// playlist?.let { viewModel.updatePlaylist(it) }
// } else {
// //insert playlist
// playlist = MoviePlaylist(
// title = binding.etTitleSong.text.toString(),
// singer = binding.etSingerName.text.toString(),
// imgIconUrl = binding.etIconUrl.text.toString(),
// videoUrl = binding.etVideoUrl.text.toString()
//
// )
// playlist?.let { viewModel.insertMediaPlaylist(it) }
//
// }
// }
// }
//
// private fun isPlaylistFilled(): Boolean {
// val title = binding.etTitleSong.text.toString()
// val singer = binding.etSingerName.text.toString()
// val imgIconUrl = binding.etIconUrl.text.toString()
// val videoUrl = binding.etVideoUrl.text.toString()
// var isFormValid = true
//
// if (title.isEmpty()) {
// isFormValid = false
// binding.tilTitleSong.isErrorEnabled = true
// binding.tilTitleSong.error = getString(R.string.addnewsong_error_title)
// } else {
// binding.tilTitleSong.isErrorEnabled = false
// }
//
// if (singer.isEmpty()) {
// isFormValid = false
// binding.tilSingerName.isErrorEnabled = true
// binding.tilSingerName.error = getString(R.string.addnewsong_error_singer)
// } else {
// binding.tilSingerName.isErrorEnabled = false
// }
//
// if (imgIconUrl.isEmpty()) {
// isFormValid = false
// binding.tilIconUrl.isErrorEnabled = true
// binding.tilIconUrl.error = getString(R.string.addnewsong_error_icon)
// } else {
// binding.tilIconUrl.isErrorEnabled = false
// }
//
// if (videoUrl.isEmpty()) {
// isFormValid = false
// binding.tilVideoUrl.isErrorEnabled = true
// binding.tilVideoUrl.error = getString(R.string.addnewsong_error_video)
// } else {
// binding.tilVideoUrl.isErrorEnabled = false
// }
// return isFormValid
// }
//
// override fun onSuccess() {
// Toast.makeText(this, getString(R.string.addnewsong_toast_success), Toast.LENGTH_SHORT).show()
// finish()
// }
//
// override fun onFailed() {
// // save playlist failed
// Toast.makeText(this, getString(R.string.addnewsong_toast_failed), Toast.LENGTH_SHORT).show()
// finish()
// }
//
// override fun getIntentData() {
// appMode = intent.getIntExtra(ARG_MODE, 0)
// playlist = intent.getParcelableExtra(ARG_PLAYLIST_DATA)
// }
//
// override fun initializePlaylist() {
// initViewModel()
// //preset data when form mode is edit mode
// if (appMode == MODE_EDIT) {
// playlist?.let {
// binding.etTitleSong.setText(it.title)
// binding.etSingerName.setText(it.singer)
// binding.etIconUrl.setText(it.imgIconUrl)
// binding.etVideoUrl.setText(it.videoUrl)
// }
// //"Edit Data"
// supportActionBar?.title = getString(R.string.addnewsong_header_edit)
// binding.btnAddSong.text = getString(R.string.addnewsong_btn_edit)
// } else {
// supportActionBar?.title = getString(R.string.addnewsong_header_add)
// }
// }
//
// override fun initView() {
// binding = ActivityAddNewSongBinding.inflate(layoutInflater)
// setSupportActionBar(binding.toolbarAddSong)
// setContentView(binding.root)
// addPlaylist()
// getIntentData()
// initializePlaylist()
// }
//
// override fun initViewModel() {
// val dataSource =
// MovieDataSource(MediaPlaylistRoomDatabase.getInstance(this).mediaPlaylistDao())
// val repository = AddNewSongRepository(dataSource)
// viewModel = GenericViewModelFactory(AddNewSongViewModel(repository)).create(AddNewSongViewModel::class.java)
//
// viewModel.transactionResult.observe(this, { isTransactionSuccess ->
// if (isTransactionSuccess) {
// onSuccess()
// } else {
// onFailed()
// }
// })
// }
//
// private fun addDialog() {
// val alertDialog = FragmentAddDialogBinding.inflate(layoutInflater)
// val builder = AlertDialog.Builder(this)
// builder.setView(alertDialog.root)
// builder.show()
// window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
// if (appMode == MODE_EDIT){
// alertDialog.tvDialogConfirmation.text = getString(R.string.addnewsong_dialog_edit)
// }
//
// alertDialog.tvDialogYes.setOnClickListener {
// //add song to playlist
// savePlaylist()
//
// }
// alertDialog.tvDialogNo.setOnClickListener {
// //back to Homepage
// onBackPressed()
// Toast.makeText(this, getString(R.string.addnewsong_toast_cancel), Toast.LENGTH_SHORT).show()
//
// }
// }