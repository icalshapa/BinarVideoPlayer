package com.binarteamtwo.binarvideoplayer.ui.fragments

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.irfan.binarvideoplayer.model.MediaPlaylist

interface MediaPlaylistContract {
    interface View : BaseContract.BaseView{
        fun getData()

        //Callback get Data
        fun onDataSuccess(playlist : List<MediaPlaylist>)
        fun onDataFailed(msg : String?)
        fun onDataEmpty()

        //callback when delete data
        fun onDeleteDataSuccess()
        fun onDeleteDataFailed()

        //setloading state and message visibility
        fun setLoadingStatus(isLoading : Boolean)
        fun setEmptyStateVisibility(isDataEmpty : Boolean)

        //initialize list
        fun initList()

    }
    interface Presenter : BaseContract.BasePresenter{
        fun getMediaPlaylistByCompleteness(isTaskComplete : Boolean)
        fun deleteMediaPlaylist(mediaPlaylist : MediaPlaylist)

    }
}