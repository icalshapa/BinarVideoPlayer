package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

interface AddNewSongContract {
    interface ViewModel{
        fun insertMediaPlaylist(mediaplaylist : MediaPlaylist)
        fun updatePlaylist(mediaplaylist : MediaPlaylist)
    }

    interface View : BaseContract.BaseView{
        fun onSuccess()
        fun onFailed()
        fun getIntentData()
        fun initializePlaylist()
    }
}