package com.binarteamtwo.binarvideoplayer.ui.homepage

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse

interface HomepageContract {
    interface ViewModel : BaseContract.ViewModel {
        fun getMovieData()
    }

    interface View : BaseContract.View {
        fun showMoviePlaylistContent(isContentVisible: Boolean)
        fun showLoading(isLoading: Boolean)
        fun showError(isErrorEnabled : Boolean, msg : String?)
        fun setupSwipeRefresh()
        fun setupList()
        fun setListData(data: List<MovieResponse>)
    }
}
