package com.binarteamtwo.binarvideoplayer.ui.homepage

import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MoviePlaylistDataSource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse

class HomepageRepository(private val movieDataSource: MoviePlaylistDataSource){
    suspend fun getMoviePlaylist() : List<MoviePlaylist> {
        return movieDataSource.getMoviePlaylist()
    }
}