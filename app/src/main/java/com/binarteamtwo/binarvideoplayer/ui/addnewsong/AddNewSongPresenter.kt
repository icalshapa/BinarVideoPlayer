package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import com.binarteamtwo.binarvideoplayer.base.BasePresenterImpl
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewSongPresenter (
    private val playlistSource: MediaPlaylistDataSource,
    private val view : AddNewSongContract.View
) : BasePresenterImpl(), AddNewSongContract.Presenter {
    override fun insertMediaPlaylist(mediaplaylist: MediaPlaylist) {
        scope.launch {
            try {
                val playlistId = playlistSource.insertMediaPlaylist(mediaplaylist)
                scope.launch (Dispatchers.Main){
                    if (playlistId > 0){
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
                val playlistId = playlistSource.updateMediaPlaylist(mediaplaylist)
                scope.launch (Dispatchers.Main){
                    if (playlistId > 0){
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