package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse

class TrailerListRepository (private val movieDataSource: MovieDataSource){
    suspend fun getMovieTrailer(id: Int): MovieResponse{
        return movieDataSource.getMovieTrailer(id)
    }
}