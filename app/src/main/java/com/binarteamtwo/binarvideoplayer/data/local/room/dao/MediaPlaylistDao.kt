package com.irfan.binarvideoplayer.local.room.dao

import androidx.room.*
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
@Dao
interface MediaPlaylistDao {
    @Query("SELECT * from mediaplaylist WHERE is_task_favorited == :isTaskComplete")
    suspend fun getTodoByCompleteness(isTaskComplete : Boolean) : List<MediaPlaylist>
    //todo : use for playlist fragment
    //@Query("SELECT * from mediaplaylist")
    //suspend fun getMediaPlaylist(mediaPlaylist: MediaPlaylist) : MediaPlaylist

    @Query("SELECT * from mediaplaylist WHERE id == :mediaPlaylistId")
    suspend fun getMediaPlaylistById(mediaPlaylistId : Int) : MediaPlaylist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaPlaylist(mediaPlaylist : MediaPlaylist) : Long

    @Delete
    suspend fun deleteMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int

    @Update
    suspend fun updateMediaPlaylist(mediaPlaylist : MediaPlaylist) : Int

}