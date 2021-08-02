package com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification

import com.google.gson.annotations.SerializedName

data class BaseAuthResponse<T, E>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: T,
    @SerializedName("errors")
    val errors: E
)
