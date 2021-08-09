package com.binarteamtwo.binarvideoplayer.ui.login

import com.binarteamtwo.binarvideoplayer.data.network.datasource.BinarDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification.LoginRequest
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.BaseAuthResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.LoginResponse

class LoginRepository(private val binarDataSource: BinarDataSource) {
    suspend fun postLoginData(loginRequest: LoginRequest) : BaseAuthResponse<LoginResponse, String> {
        return binarDataSource.postLoginData(loginRequest)
    }
}