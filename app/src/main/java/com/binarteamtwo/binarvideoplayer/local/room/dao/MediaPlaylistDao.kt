package com.irfan.binarvideoplayer.local.room.dao

import androidx.room.*
import com.irfan.binarvideoplayer.model.MediaPlaylist
@Dao
interface MediaPlaylistDao {
    @Query("SELECT * from mediaplaylist WHERE is_task_favorite")
    suspend fun getFavoriteMediaPlaylist() : List<MediaPlaylist>

    @Query("SELECT * from mediaplaylist")
    suspend fun getMediaPlaylist() : List<MediaPlaylist>

    @Query("SELECT * from mediaplaylist WHERE id == :mediaPlaylistId")
    suspend fun getMediaPlaylistById(mediaPlaylistId : Int) : MediaPlaylist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaPlaylist(mediaPlaylist : MediaPlaylist) : Long

    @Delete
    suspend fun deleteMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int

    @Update
    suspend fun updateMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int

}