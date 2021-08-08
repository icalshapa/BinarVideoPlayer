package com.binarteamtwo.binarvideoplayer.ui.favourite

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.model.MoviePlaylist
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie

interface FavouriteContract {
    interface View : BaseContract.View {
        fun showMoviePlaylistContent(isContentVisible: Boolean)
        fun showLoading(isLoading: Boolean)
        fun showError(isErrorEnabled : Boolean, msg : String?)
        fun setupSwipeRefresh()
        fun setupList()
        fun setListData(data: List<Movie>)
    }

    interface ViewModel : BaseContract.ViewModel{
        fun getMovieDataPopular()
    }
}