package com.binarteamtwo.binarvideoplayer.data.local.room.datasource

import com.binarteamtwo.binarvideoplayer.data.local.room.dao.MediaPlaylistDao
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist

class MediaPlaylistDataSource(private val mediaPlaylistDao: MediaPlaylistDao) {
    suspend fun insertMediaPlaylist(mediaPlaylist : MediaPlaylist) : Long{
        return mediaPlaylistDao.insertMediaPlaylist(mediaPlaylist)
    }
    suspend fun updateMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int{
        return mediaPlaylistDao.updateMediaPlaylist(mediaPlaylist)
    }
    suspend fun deleteMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int{
        return mediaPlaylistDao.deleteMediaPlaylist(mediaPlaylist)
    }

    suspend fun getFavoriteMediaPlaylist() : List<MediaPlaylist>{
        return mediaPlaylistDao.getFavoriteMediaPlaylist()
    }
    suspend fun getMediaPlaylist() : List<MediaPlaylist>{
        return mediaPlaylistDao.getMediaPlaylist()
    }

    suspend fun getMediaPlaylistById(mediaPlaylistId : Int) : MediaPlaylist {
        return mediaPlaylistDao.getMediaPlaylistById(mediaPlaylistId)
    }
}