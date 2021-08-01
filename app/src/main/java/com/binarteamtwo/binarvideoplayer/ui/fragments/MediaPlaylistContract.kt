package com.binarteamtwo.binarvideoplayer.ui.fragments

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

interface MediaPlaylistContract {
    interface View : BaseContract.BaseView{
        fun getData(isFavorite: Boolean)

        //Callback get Data
        fun onDataSuccess(playlist : List<MediaPlaylist>)
        fun onDataFailed(msg : String?)
        fun onDataEmpty()

        //callback when delete data
        fun onDeleteDataSuccess()
        fun onDeleteDataFailed()

        //set loading state and message visibility
        fun setLoadingStatus(isLoading : Boolean)
        fun setEmptyStateVisibility(isDataEmpty : Boolean)

        //initialize list
        fun initList()

    }
    interface ViewModel{
        fun getFavoriteMediaPlaylist(isFavorite:Boolean)
        fun deleteMediaPlaylist(mediaPlaylist : MediaPlaylist)

    }
}