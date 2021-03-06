package com.binarteamtwo.binarvideoplayer.data.network.datasource

import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieTrailer
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices

class MovieDataSource(private val movieApiServices: MovieApiServices) {
    suspend fun getMovie() : MovieResponse {
        return movieApiServices.getMovie()
    }
    suspend fun getMoviePopular() : MovieResponse{
        return movieApiServices.getMoviePopular()
    }
    suspend fun getMovieTrailer(id: Int): MovieTrailer{
        return movieApiServices.getMovieTrailer(id)
    }
}