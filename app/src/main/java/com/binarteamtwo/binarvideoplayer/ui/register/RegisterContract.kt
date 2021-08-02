package com.binarteamtwo.binarvideoplayer.ui.register

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.request.authentification.RegisterRequest

interface RegisterContract {
    interface ViewModel : BaseContract.ViewModel {
        fun registerUser(registerRequest: RegisterRequest)
    }

    interface BaseView : BaseContract.BaseView {
        fun setToolbar()
        fun setOnClick()
        fun setLoadingState(isLoadingVisible: Boolean)
        fun checkFormValidation() : Boolean
        fun navigateToLogin()
    }
}