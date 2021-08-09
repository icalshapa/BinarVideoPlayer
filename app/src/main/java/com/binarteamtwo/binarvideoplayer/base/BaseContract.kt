package com.binarteamtwo.binarvideoplayer.base

interface BaseContract {
    interface ViewModel{

    }
    interface BaseView{
        fun initView()
        fun initViewModel()
    }
}
