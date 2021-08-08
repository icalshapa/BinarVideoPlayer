package com.binarteamtwo.binarvideoplayer.data.network.entity.response


import com.google.gson.annotations.SerializedName

data class MovieTrailer(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("results")
    var results: List<ResultX>? = null
)