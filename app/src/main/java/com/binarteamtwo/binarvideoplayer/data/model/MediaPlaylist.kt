package com.binarteamtwo.binarvideoplayer.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "mediaplaylist")
data class MediaPlaylist (
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0,
    @ColumnInfo(name ="title")
    var title : String?,
    @ColumnInfo(name ="singer")
    var singer : String?,
    @ColumnInfo(name ="img_icon_url")
    var imgIconUrl: String?,
    @ColumnInfo(name ="video_url")
    var videoUrl: String?,
    @ColumnInfo(name ="is_task_favorited")
    var isVideoFavorited: Boolean = false
) : Parcelable