package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

class AddNewSongRepository(private val dataSource: MediaPlaylistDataSource) {
    suspend fun insertMediaPlaylist(mediaPlaylist : MediaPlaylist) : Long{
        return dataSource.insertMediaPlaylist(mediaPlaylist)
    }
    suspend fun updateMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int{
        return dataSource.updateMediaPlaylist(mediaPlaylist)
    }
}