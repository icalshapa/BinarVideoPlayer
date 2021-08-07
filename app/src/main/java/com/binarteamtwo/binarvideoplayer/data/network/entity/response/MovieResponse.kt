package com.binarteamtwo.binarvideoplayer.data.network.entity.response


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: Results? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
) : List<Results>