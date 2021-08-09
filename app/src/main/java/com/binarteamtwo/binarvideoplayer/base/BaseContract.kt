package com.binarteamtwo.binarvideoplayer.base

interface BaseContract {
    interface ViewModel{
    }

    interface View{
        fun initView()
        fun initViewModel()
    }
}