package com.binarteamtwo.binarvideoplayer.ui.player

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist

interface PlayerContract {
    interface View: BaseContract.BaseView{

        //callback when get video
        fun onFetchVideoSuccess(moviePlaylist: MoviePlaylist)
        fun onFetchVideoFailed()

        //callback when change favorite status
        fun onChangeFavoriteStatusSuccess(moviePlaylist: MoviePlaylist)
        fun onChangeFavoriteStatusFailed()

        //set data to layout
        fun bindVideoData(moviePlaylist: MoviePlaylist?)

        //getting data
        fun getData()
    }

    interface ViewModel {
        fun getVideo(videoId: Int)
        fun changeStatusFavorite(moviePlaylist: MoviePlaylist)
    }


}