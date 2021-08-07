package com.binarteamtwo.binarvideoplayer.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomepageViewModel(private val repository: HomepageRepository) : ViewModel(),
    HomepageContract.ViewModel {

    val movieData = MutableLiveData<Resource<List<MoviePlaylist>>>()

    override fun getMovieData() {
        movieData.value = Resource.Loading()
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.getMovie()
                viewModelScope.launch (Dispatchers.Main){
                    movieData.value = Resource.Success(response)
                }
            } catch (e: Exception){
                viewModelScope.launch (Dispatchers.Main){
                    movieData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
    }
