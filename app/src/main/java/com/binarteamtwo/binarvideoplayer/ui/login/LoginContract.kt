package com.binarteamtwo.binarvideoplayer.ui.login

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.request.authentification.LoginRequest

interface LoginContract {
    interface ViewModel : BaseContract.ViewModel{
        fun loginUser(loginRequest: LoginRequest)
    }
    interface BaseView : BaseContract.BaseView{
        fun setToolbar()
        fun setOnClick()
        fun navigateToHome()
        fun navigateToRegister()
        fun setLoadingState(isLoadingVisible: Boolean)
        fun checkFormValidation() : Boolean
        fun saveSessionLogin(authToken : String)
    }
}