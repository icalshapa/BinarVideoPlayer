package com.binarteamtwo.binarvideoplayer.data.network.datasource

import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices

class MovieDataSource(private val movieApiServices: MovieApiServices)  {
    suspend fun getMovieData() : MovieResponse{
        return movieApiServices.getMovie()
    }

}