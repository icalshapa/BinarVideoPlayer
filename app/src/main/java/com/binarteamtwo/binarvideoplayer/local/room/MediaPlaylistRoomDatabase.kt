package com.binarteamtwo.binarvideoplayer.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.irfan.binarvideoplayer.model.MediaPlaylist
import com.irfan.binarvideoplayer.local.room.dao.MediaPlaylistDao


@Database(entities = [MediaPlaylist::class], version = 1)
abstract class MediaPlaylistRoomDatabase : RoomDatabase() {

    abstract fun mediaPlaylistDao() : MediaPlaylistDao

    companion object {
        @Volatile
        private var INSTANCE: MediaPlaylistRoomDatabase? = null
        fun getInstance(context: Context): MediaPlaylistRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MediaPlaylistRoomDatabase::class.java,
                    "mediaplayer_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}