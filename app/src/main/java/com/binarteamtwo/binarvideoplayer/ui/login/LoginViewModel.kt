package com.binarteamtwo.binarvideoplayer.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.request.authentification.LoginRequest
import com.binarteamtwo.binarvideoplayer.data.network.entitiy.response.authentification.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel(),
    LoginContract.ViewModel {
    val loginResponse = MutableLiveData<Resource<LoginResponse>>()

    override fun loginUser(loginRequest: LoginRequest) {
        //todo : set payload to loading
        loginResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLoginData(loginRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    //checking if response success
                    if (response.success) {
                        loginResponse.value = Resource.Success(response.data)
                    } else {
                        loginResponse.value = Resource.Error(response.errors)
                    }
                }
            } catch (e: Exception) {
                //set value to error message
                viewModelScope.launch(Dispatchers.Main) {
                    loginResponse.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

}