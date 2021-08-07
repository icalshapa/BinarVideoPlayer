package com.binarteamtwo.binarvideoplayer.ui.player

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist

class PlayerRepository(private val dataSource: MovieDataSource)/* {
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

}*/