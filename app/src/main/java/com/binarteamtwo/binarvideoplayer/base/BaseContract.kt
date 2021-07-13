package com.binarteamtwo.binarvideoplayer.base

interface BaseContract {
    interface BasePresenter{
        fun onDestroy()
    }
    interface BaseView{
        fun initView()
    }
}