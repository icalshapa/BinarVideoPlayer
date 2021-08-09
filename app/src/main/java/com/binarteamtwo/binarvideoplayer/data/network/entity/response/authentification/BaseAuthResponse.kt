package com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class BaseAuthResponse<T, E>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: T,
    @SerializedName("errors")
    val errors: E
)