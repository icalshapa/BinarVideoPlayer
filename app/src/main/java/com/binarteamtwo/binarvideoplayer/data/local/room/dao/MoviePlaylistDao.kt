package com.binarteamtwo.binarvideoplayer.data.local.room.dao

import androidx.room.*
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
@Dao
interface MoviePlaylistDao {
    @Query("SELECT * from movieplaylist WHERE is_movie_favorite")
    suspend fun getFavoriteMoviePlaylist() : List<MoviePlaylist>

    @Query("SELECT * from movieplaylist")
    suspend fun getMoviePlaylist() : List<MoviePlaylist>

    @Query("SELECT * from movieplaylist WHERE id == :moviePlaylistId")
    suspend fun getMoviePlaylistById(moviePlaylistId : Int) : MoviePlaylist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePlaylist(moviePlaylist : MoviePlaylist) : Long

    @Delete
    suspend fun deleteMoviePlaylist(moviePlaylist : MoviePlaylist) : Int

    @Update
    suspend fun updateMoviePlaylist(moviePlaylist : MoviePlaylist) : Int

}