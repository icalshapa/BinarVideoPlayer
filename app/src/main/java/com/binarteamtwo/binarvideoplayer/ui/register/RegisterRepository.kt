package com.binarteamtwo.binarvideoplayer.ui.register

import com.binarteamtwo.binarvideoplayer.data.network.datasource.BinarDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification.RegisterRequest
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.BaseAuthResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.UserResponse

class RegisterRepository(private val binarDataSource: BinarDataSource) {
    suspend fun postRegisterData(registerRequest: RegisterRequest) : BaseAuthResponse<UserResponse, String> {
        return binarDataSource.postRegisterData(registerRequest)
    }
}