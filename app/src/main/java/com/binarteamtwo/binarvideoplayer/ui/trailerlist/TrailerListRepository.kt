package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieTrailer

class TrailerListRepository (private val movieDataSource: MovieDataSource){
    suspend fun getMovieTrailer(id: Int): MovieTrailer{
        return movieDataSource.getMovieTrailer(id)
    }
}