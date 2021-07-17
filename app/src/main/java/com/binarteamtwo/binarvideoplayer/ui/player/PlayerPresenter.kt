package com.binarteamtwo.binarvideoplayer.ui.player

import com.binarteamtwo.binarvideoplayer.base.BasePresenterImpl
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val dataSource: MediaPlaylistDataSource,
    private val view: PlayerContract.View
): BasePresenterImpl(), PlayerContract.Presenter {
    override fun getVideo(videoId: Int) {
        scope.launch {
            try {
                val video = dataSource.getMediaPlaylistById(videoId)
                scope.launch (Dispatchers.Main){
                    view.onFetchVideoSuccess(video)
                }
            }catch (e:Exception){
                scope.launch (Dispatchers.Main){
                    view.onFetchVideoFailed()
                }
            }
        }
    }

    override fun changeStatusFavorite(mediaPlaylist: MediaPlaylist) {
        scope.launch {
            try {
                val videoFavorite = dataSource.changeMediaPlaylistStatus(mediaPlaylist)
                scope.launch (Dispatchers.Main){
                    view.onChangeFavoriteStatusSuccess(videoFavorite)
                }
            }catch (e:Exception){
                scope.launch (Dispatchers.Main){
                    view.onChangeFavoriteStatusFailed()
                }
            }
        }
    }
}