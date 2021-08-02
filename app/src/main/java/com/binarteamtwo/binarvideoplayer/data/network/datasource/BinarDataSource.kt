package com.binarteamtwo.binarvideoplayer.data.network.datasource

import com.binarteamtwo.binarvideoplayer.data.network.entitiy.request.authentification.LoginRequest
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.request.authentification.RegisterRequest
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification.BaseAuthResponse
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification.LoginResponse
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification.UserResponse
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.services.BinarApiServices

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