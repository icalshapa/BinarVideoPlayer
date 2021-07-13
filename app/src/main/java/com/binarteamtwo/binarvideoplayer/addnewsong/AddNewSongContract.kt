package com.binarteamtwo.binarvideoplayer.addnewsong

import com.binarteamtwo.binarvideoplayer.base.BaseContract

interface AddNewSongContract {
    interface Presenter : BaseContract.BasePresenter{
        fun insertPlaylist(mediaplaylist : MediaPlaylist)
        fun updatePlaylist(mediaplaylist : MediaPlaylist)
    }

    interface View : BaseContract.BaseView{
        fun onSuccess()
        fun onFailed()
        fun getIntentData()
        fun initializePlaylist()
    }
}