package com.binarteamtwo.binarvideoplayer.ui.splashscreen

import com.binarteamtwo.binarvideoplayer.data.network.datasource.BinarDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification.BaseAuthResponse
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification.UserResponse

class SplashScreenRepository(private val binarDataSource: BinarDataSource) {
    suspend fun getSyncData() : BaseAuthResponse<UserResponse, String> {
        return binarDataSource.getSyncData()
    }
}