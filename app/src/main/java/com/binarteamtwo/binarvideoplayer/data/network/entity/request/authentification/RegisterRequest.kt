package com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    private var email : String,
    @SerializedName("username")
    private var username : String,
    @SerializedName("password")
    private var password : String
)
