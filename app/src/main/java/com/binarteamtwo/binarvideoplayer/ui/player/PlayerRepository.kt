package com.binarteamtwo.binarvideoplayer.ui.player

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MoviePlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist

class PlayerRepository(private val dataSource: MoviePlaylistDataSource) {
    suspend fun getMediaPlaylistById(mediaPlaylistId : Int) : MoviePlaylist{
        return dataSource.getMoviePlaylistById(mediaPlaylistId)
    }
    suspend fun changeMediaPlaylistStatus(moviePlaylist: MoviePlaylist):MoviePlaylist{
        val updatedMediaPlaylist = moviePlaylist.copy().apply {
            this.isFavorite = isFavorite.not()
        }
        dataSource.updateMoviePlaylist(updatedMediaPlaylist)
        return getMediaPlaylistById(moviePlaylist.id)
    }

}