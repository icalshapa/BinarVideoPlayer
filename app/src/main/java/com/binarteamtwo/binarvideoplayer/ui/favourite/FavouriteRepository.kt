package com.binarteamtwo.binarvideoplayer.ui.favourite

import com.binarteamtwo.binarvideoplayer.data.local.room.dao.MoviePlaylistDao
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse

class FavouriteRepository (private val movieDataSource: MovieDataSource) {
    suspend fun getMoviePopular() : MovieResponse {
        return movieDataSource.getMoviePopular()
    }

}