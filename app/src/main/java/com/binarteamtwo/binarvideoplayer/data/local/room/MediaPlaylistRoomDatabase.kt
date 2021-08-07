package com.binarteamtwo.binarvideoplayer.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import com.binarteamtwo.binarvideoplayer.data.local.room.dao.MoviePlaylistDao


@Database(entities = [MoviePlaylist::class], version = 1)
abstract class MediaPlaylistRoomDatabase : RoomDatabase() {

    abstract fun mediaPlaylistDao() : MoviePlaylistDao

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