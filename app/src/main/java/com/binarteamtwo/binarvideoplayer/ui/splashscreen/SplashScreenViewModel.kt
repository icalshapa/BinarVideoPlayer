package com.binarteamtwo.binarvideoplayer.ui.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.authentification.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val repository: SplashScreenRepository
) : ViewModel(),
    SplashScreenContract.ViewModel {
    val syncData = MutableLiveData<Resource<UserResponse>>()

    override fun getSyncData() {
        //todo : set payload to loading
        syncData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getSyncData()
                viewModelScope.launch(Dispatchers.Main) {
                    //checking if response success
                    if (response.success) {
                        syncData.value = Resource.Success(response.data)
                    } else {
                        syncData.value = Resource.Error(response.errors)
                    }
                }
            } catch (e: Exception) {
                //set value to error message
                viewModelScope.launch(Dispatchers.Main) {
                    syncData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

}