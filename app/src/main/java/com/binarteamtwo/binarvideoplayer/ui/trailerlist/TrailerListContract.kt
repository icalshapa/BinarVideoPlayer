package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import com.binarteamtwo.binarvideoplayer.base.BaseContract
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie

interface TrailerListContract {
    interface ViewModel : BaseContract.ViewModel {
        fun getTrailerData(id: Int)
    }

    interface View : BaseContract.View {
        fun showContent(isContentVisible: Boolean)
        fun showLoading(isLoading: Boolean)
        fun showError(isErrorEnabled : Boolean, msg : String?)
        fun setupSwipeRefresh()
        fun setupList()
        fun setListData(data: List<Movie>)
        fun getIntentData()
    }
}