package com.binarteamtwo.binarvideoplayer.ui.fragments

import com.binarteamtwo.binarvideoplayer.local.room.datasource.MediaPlaylistDataSource
import com.irfan.binarvideoplayer.base.BasePresenterImpl
import com.irfan.binarvideoplayer.model.MediaPlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MediaPlaylistPresenter(
    private val dataSource: MediaPlaylistDataSource,
    private val view: MediaPlaylistContract.View
) : BasePresenterImpl(), MediaPlaylistContract.Presenter {
    override fun getMediaPlaylistByCompleteness(isTaskComplete: Boolean) {
        view.setLoadingStatus(true)
        scope.launch {
            try {
                val mediaPlaylists = dataSource.getMediaPlaylistByCompleteness(isTaskComplete)
                scope.launch(Dispatchers.Main) {
                    if (!mediaPlaylists.isNullOrEmpty()) {
                        //data is not empty
                        view.onDataSuccess(mediaPlaylists)
                        view.setEmptyStateVisibility(false)
                    } else {
                        view.onDataEmpty()
                        view.setEmptyStateVisibility(true)
                    }
                    view.setLoadingStatus(false)
                }
            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                    //when getting data is error
                    view.onDataFailed(e.message)
                    view.setLoadingStatus(false)
                    view.setEmptyStateVisibility(false)
                }

            }
        }
    }

    override fun deleteMediaPlaylist(mediaPlaylist: MediaPlaylist) {
        view.setLoadingStatus(true)
        scope.launch {
            try {
                val result = dataSource.deleteMediaPlaylist(mediaPlaylist)
                scope.launch(Dispatchers.Main) {
                    if (result.equals(1)) {
                        view.onDeleteDataSuccess()

                    } else {
                        view.onDeleteDataFailed()


                    }
                    view.setLoadingStatus(false)
                }
            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                    //when getting data is error
                    view.onDeleteDataFailed()
                    view.setLoadingStatus(false)

                }

            }
        }

    }


}