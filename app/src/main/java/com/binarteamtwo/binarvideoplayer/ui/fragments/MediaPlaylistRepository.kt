package com.binarteamtwo.binarvideoplayer.ui.fragments

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

class MediaPlaylistRepository (private val dataSource: MediaPlaylistDataSource){
    suspend fun getMediaPlaylist() : List<MediaPlaylist>{
        return dataSource.getMediaPlaylist()
    }
    suspend fun getFavoriteMediaPlaylist() : List<MediaPlaylist>{
        return dataSource.getFavoriteMediaPlaylist()
    }
    suspend fun deleteMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int{
        return dataSource.deleteMediaPlaylist(mediaPlaylist)
    }
}