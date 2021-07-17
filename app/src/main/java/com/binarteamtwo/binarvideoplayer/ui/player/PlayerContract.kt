package com.binarteamtwo.binarvideoplayer.ui.player

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

interface PlayerContract {
    interface View: BaseContract.BaseView{

        //callback when get video
        fun onFetchVideoSuccess(mediaPlaylist: MediaPlaylist)
        fun onFetchVideoFailed()

        //callback when change favorite status
        fun onChangeFavoriteStatusSuccess(mediaPlaylist: MediaPlaylist)
        fun onChangeFavoriteStatusFailed()

        //set data to layout
        fun bindVideoData(mediaPlaylist: MediaPlaylist?)

        //getting data
        fun getData()
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getVideo(videoId: Int)
        fun changeStatusFavorite(mediaPlaylist: MediaPlaylist)
    }


}