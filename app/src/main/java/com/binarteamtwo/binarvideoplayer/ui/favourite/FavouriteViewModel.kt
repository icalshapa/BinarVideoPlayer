package com.binarteamtwo.binarvideoplayer.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageContract
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel (private val repository: FavouriteRepository) : ViewModel(),
    FavouriteContract.ViewModel {

    val movieData = MutableLiveData<Resource<MovieResponse>>()

    override fun getMovieDataPopular() {
        movieData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMoviePopular()
                viewModelScope.launch(Dispatchers.Main) {
                    movieData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    movieData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}