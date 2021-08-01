package com.binarteamtwo.binarvideoplayer.ui.player

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

class PlayerRepository(private val dataSource: MediaPlaylistDataSource) {
    suspend fun getMediaPlaylistById(mediaPlaylistId : Int) : MediaPlaylist{
        return dataSource.getMediaPlaylistById(mediaPlaylistId)
    }
    suspend fun changeMediaPlaylistStatus(mediaPlaylist: MediaPlaylist):MediaPlaylist{
        val updatedMediaPlaylist = mediaPlaylist.copy().apply {
            this.isFavorite = isFavorite.not()
        }
        dataSource.updateMediaPlaylist(updatedMediaPlaylist)
        return getMediaPlaylistById(mediaPlaylist.id)
    }

}