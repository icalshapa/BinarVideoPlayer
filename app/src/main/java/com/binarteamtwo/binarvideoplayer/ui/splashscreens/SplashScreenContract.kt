package com.binarteamtwo.binarvideoplayer.ui.splashscreens

import com.binarteamtwo.binarvideoplayer.base.BaseContract

interface SplashScreenContract {
    interface ViewModel : BaseContract.ViewModel{
        fun getSyncData()
    }
    interface BaseView : BaseContract.BaseView{
        fun navigateToIntro()
        fun navigateToHome()
        fun checkLogin()
        fun deleteSessionIntroAndLogin()
    }
}