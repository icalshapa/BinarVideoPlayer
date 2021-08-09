package com.binarteamtwo.binarvideoplayer.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.network.entity.request.authentification.RegisterRequest
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel(),
    RegisterContract.ViewModel {
    val registerResponse = MutableLiveData<Resource<UserResponse>>()

    override fun registerUser(registerRequest: RegisterRequest) {
        //todo : set payload to loading
        registerResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postRegisterData(registerRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    //checking if response success
                    if (response.success) {
                        registerResponse.value = Resource.Success(response.data)
                    } else {
                        registerResponse.value = Resource.Error(response.errors)
                    }
                }
            } catch (e: Exception) {
                //set value to error message
                viewModelScope.launch(Dispatchers.Main) {
                    registerResponse.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }


}