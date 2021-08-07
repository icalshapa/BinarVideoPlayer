package com.binarteamtwo.binarvideoplayer.data.network.datasource

import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieDetail
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieDetailApiServices

class MovieDetailDataSource(private val movieDetailApiServices: MovieDetailApiServices) {
    suspend fun getMovieDetail(movieId : String): MovieDetail {
        return movieDetailApiServices.getMovieDetails(movieId)
    }
}