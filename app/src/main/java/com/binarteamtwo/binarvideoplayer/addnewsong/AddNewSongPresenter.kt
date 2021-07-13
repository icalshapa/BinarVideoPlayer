package com.binarteamtwo.binarvideoplayer.addnewsong

import com.binarteamtwo.binarvideoplayer.base.BasePresenterImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewSongPresenter (
    private val playlistSource: PlaylistDataSource,
    private val view : AddNewSongContract.View
) : BasePresenterImpl(), AddNewSongContract.Presenter {
    override fun insertPlaylist(mediaplaylist: MediaPlaylist) {
        scope.launch {
            try {
                val playlistId = playlistSource.addPlaylist(mediaplaylist)
                scope.launch (Dispatchers.Main){
                    if (mediaplaylist > 0){
                        view.onSuccess()
                    } else {
                        view.onFailed()
                    }
                }
            } catch (e: Exception){
                scope.launch (Dispatchers.Main){
                    view.onFailed()
                }
            }
        }
    }

    override fun updatePlaylist(mediaplaylist: MediaPlaylist) {
        scope.launch {
            try {
                val playlistId = playlistSource.updatePlaylist(mediaplaylist)
                scope.launch (Dispatchers.Main){
                    if (mediaplaylist > 0){
                        view.onSuccess()
                    } else {
                        view.onFailed()
                    }
                }
            } catch (e: Exception){
                scope.launch (Dispatchers.Main){
                    view.onFailed()
                }
            }
        }
    }
}