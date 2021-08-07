package com.binarteamtwo.binarvideoplayer.ui.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: PlayerRepository):ViewModel(),PlayerContract.ViewModel {
    val playerData = MutableLiveData<Resource<MoviePlaylist>>()
    val changeStatusResult = MutableLiveData<Pair<Boolean, MoviePlaylist?>>()
    override fun getVideo(videoId: Int) {
        playerData.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val video = repository.getMediaPlaylistById(videoId)
                viewModelScope.launch(Dispatchers.Main) {
                    playerData.value = Resource.Success(video)
                }

            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    playerData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun changeStatusFavorite(moviePlaylist: MoviePlaylist) {
        viewModelScope.launch {
            try {
                val video = repository.changeMediaPlaylistStatus(moviePlaylist)
                viewModelScope.launch(Dispatchers.Main) {
                    changeStatusResult.value = Pair(true,video)
                }

            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    changeStatusResult.value = Pair(false,null)
                }
            }
        }
    }
}