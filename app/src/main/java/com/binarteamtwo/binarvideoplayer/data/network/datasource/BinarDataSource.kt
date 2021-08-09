package com.binarteamtwo.binarvideoplayer.data.network.datasource

import com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification.LoginRequest
import com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification.RegisterRequest
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.BaseAuthResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.LoginResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.UserResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.services.BinarApiServices

class BinarDataSource(private val binarApiServices: BinarApiServices) {
    suspend fun postLoginData(loginRequest: LoginRequest) : BaseAuthResponse<LoginResponse, String> {
        return binarApiServices.postLoginData(loginRequest)
    }

    suspend fun postRegisterData(registerRequest: RegisterRequest) : BaseAuthResponse<UserResponse, String>{
        return binarApiServices.postRegisterData(registerRequest)

    }
    suspend fun getSyncData() : BaseAuthResponse<UserResponse, String>{
        return binarApiServices.getSyncData()
    }
}