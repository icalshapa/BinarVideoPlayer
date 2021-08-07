package com.binarteamtwo.binarvideoplayer.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "movieplaylist")
data class MoviePlaylist (
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0,
    @ColumnInfo(name ="movie title")
    var movieTitle : String?,
    @ColumnInfo(name ="movie description")
    var movieDesc : String?,
    @ColumnInfo(name ="is_movie_favorite")
    var isFavorite: Boolean = false
) : Parcelable