package com.binarteamtwo.binarvideoplayer.data.network.entity.response


import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int? = null,
    @SerializedName("iso_3166_1")
    var iso31661: String? = null,
    @SerializedName("iso_639_1")
    var iso6391: String? = null,
    @SerializedName("key")
    var key: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("official")
    var official: Boolean? = null,
    @SerializedName("published_at")
    var publishedAt: String? = null,
    @SerializedName("site")
    var site: String? = null,
    @SerializedName("size")
    var size: Int? = null,
    @SerializedName("type")
    var type: String? = null
)