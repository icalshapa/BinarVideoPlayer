package com.binarteamtwo.binarvideoplayer.ui.homepage

import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse

class HomepageRepository(private val movieDataSource: MovieDataSource){
    suspend fun getMovie() : Movie {
        return movieDataSource.getMovie()
    }
}