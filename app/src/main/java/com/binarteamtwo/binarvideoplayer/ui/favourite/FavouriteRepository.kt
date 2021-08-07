package com.binarteamtwo.binarvideoplayer.ui.favourite

import com.binarteamtwo.binarvideoplayer.data.local.room.dao.MoviePlaylistDao
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist

class FavouriteRepository (private val moviePlaylistDao: MoviePlaylistDao ) {
    suspend fun getFavouriteMovie() : List<MoviePlaylist>{
        return moviePlaylistDao.getFavoriteMoviePlaylist()
    }
    suspend fun deleteFavouriteMovie(moviePlaylist : MoviePlaylist) : Int{
        return moviePlaylistDao.deleteMoviePlaylist(moviePlaylist)
    }

}