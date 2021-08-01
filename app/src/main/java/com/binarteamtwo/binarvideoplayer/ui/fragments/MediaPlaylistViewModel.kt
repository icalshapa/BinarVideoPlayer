package com.binarteamtwo.binarvideoplayer.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MediaPlaylistViewModel(private val repository: MediaPlaylistRepository) : ViewModel(),
    MediaPlaylistContract.ViewModel {

    val videoData = MutableLiveData<Resource<List<MediaPlaylist>>>()
    val deleteResponse = MutableLiveData<Boolean>()

    override fun getFavoriteMediaPlaylist(isFavorite: Boolean) {
        videoData.value = Resource.Loading()
        viewModelScope.launch (Dispatchers.IO){
            try {
                val mediaPlaylists = if (isFavorite){
                    repository.getFavoriteMediaPlaylist()
                }else{
                    repository.getMediaPlaylist()
                }
                viewModelScope.launch (Dispatchers.Main){
                    videoData.value = Resource.Success(mediaPlaylists)
                }
            } catch (e: Exception){
                viewModelScope.launch (Dispatchers.Main){
                    videoData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun deleteMediaPlaylist(mediaPlaylist: MediaPlaylist) {
        viewModelScope.launch {
            try {
                val result = repository.deleteMediaPlaylist(mediaPlaylist)
                viewModelScope.launch(Dispatchers.Main){
                    deleteResponse.value = result == 1
                }
            } catch (e: Exception){
                viewModelScope.launch(Dispatchers.Main){
                    deleteResponse.value = false
                }
            }
        }
    }
}