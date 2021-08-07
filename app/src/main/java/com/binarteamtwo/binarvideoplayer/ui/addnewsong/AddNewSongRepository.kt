package com.binarteamtwo.binarvideoplayer.ui.addnewsong

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MoviePlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist

class AddNewSongRepository(private val dataSource: MoviePlaylistDataSource) {
    suspend fun insertMediaPlaylist(moviePlaylist : MoviePlaylist) : Long{
        return dataSource.insertMoviePlaylist(moviePlaylist)
    }
    suspend fun updateMediaPlaylist(moviePlaylist : MoviePlaylist) : Int{
        return dataSource.updateMoviePlaylist(moviePlaylist)
    }
}