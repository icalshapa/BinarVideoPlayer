package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieTrailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrailerListViewModel(private val repository: TrailerListRepository):ViewModel(),
TrailerListContract.ViewModel{
    val trailerData = MutableLiveData<Resource<MovieTrailer>>()

    override fun getTrailerData(id: Int) {
        trailerData.value = Resource.Loading()
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.getMovieTrailer(id)
                viewModelScope.launch(Dispatchers.Main) {
                    trailerData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    trailerData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}