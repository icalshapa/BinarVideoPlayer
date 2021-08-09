package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.databinding.ActivityTrailerListBinding

class TrailerListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityTrailerListBinding
    private var trailerListFragment = TrailerListFragment()
    private var activeFragment: Fragment = trailerListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
    }

    private fun setupFragment() {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        supportFragmentManager.beginTransaction().apply{
            add(R.id.fl_fragment_trailer_list, trailerListFragment, Constant.TAG_FRAGMENT_TRAILER)
        }.commit()
    }

}
