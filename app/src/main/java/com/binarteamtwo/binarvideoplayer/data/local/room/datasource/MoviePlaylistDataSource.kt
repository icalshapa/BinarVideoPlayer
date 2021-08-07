package com.binarteamtwo.binarvideoplayer.data.local.room.datasource

import com.binarteamtwo.binarvideoplayer.data.local.room.dao.MoviePlaylistDao
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices

class MoviePlaylistDataSource(private val moviePlaylistDao: MovieApiServices) {/*
    suspend fun insertMoviePlaylist(moviePlaylist : MoviePlaylist) : Long{
        return moviePlaylistDao.insertMoviePlaylist(moviePlaylist)
    }
    suspend fun updateMoviePlaylist(moviePlaylist : MoviePlaylist) : Int{
        return moviePlaylistDao.updateMoviePlaylist(moviePlaylist)
    }
    suspend fun deleteMoviePlaylist(moviePlaylist : MoviePlaylist) : Int{
        return moviePlaylistDao.deleteMoviePlaylist(moviePlaylist)
    }

    suspend fun getFavoriteMoviePlaylist() : List<MoviePlaylist>{
        return moviePlaylistDao.getFavoriteMoviePlaylist()
    }
    suspend fun getMoviePlaylist() : List<MoviePlaylist>{
        return moviePlaylistDao.getMoviePlaylist()
    }

    suspend fun getMoviePlaylistById(moviePlaylistId : Int) : MoviePlaylist {
        return moviePlaylistDao.getMoviePlaylistById(moviePlaylistId)
    }*/
}