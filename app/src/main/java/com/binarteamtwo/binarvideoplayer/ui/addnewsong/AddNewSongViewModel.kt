package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
class AddNewSongViewModel (private val repository: AddNewSongRepository): ViewModel(),
    AddNewSongContract.ViewModel{

    val transactionResult = MutableLiveData<Boolean>()

    override fun insertMediaPlaylist(mediaplaylist: MoviePlaylist) {
        viewModelScope.launch {
            try {
                val mediaPlaylistId = repository.insertMediaPlaylist(mediaplaylist)
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = mediaPlaylistId > 0
                }
            } catch (e: Exception){
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = false
                }
            }
        }
    }

    override fun updatePlaylist(mediaplaylist: MoviePlaylist) {
        viewModelScope.launch {
            try {
                val mediaPlaylistId = repository.updateMediaPlaylist(mediaplaylist)
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = mediaPlaylistId > 0
                }
            } catch (e: Exception){
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = false
                }
            }
        }
    }
}*/
