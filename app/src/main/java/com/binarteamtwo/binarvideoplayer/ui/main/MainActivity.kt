package com.binarteamtwo.binarvideoplayer.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.ActivityMainBinding
import com.binarteamtwo.binarvideoplayer.ui.fragments.MediaPlaylistFragment
import com.binarteamtwo.binarvideoplayer.utils.views.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.irfan.binarvideoplayer.model.MediaPlaylist

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }
    //todo : waiting for AddSongForm and AboutDialog
    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_todo -> {
                navigateToTodoForm()
                true
            }
            R.id.menu_about -> {
                openDialogAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(MediaPlaylistFragment.newInstance(false), "Playlist")
        fragmentAdapter.addFragment(MediaPlaylistFragment.newInstance(true), "Favorite")
        binding.viewPager.apply {
            adapter = fragmentAdapter
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager, true) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }

    private fun navigateToAddSongForm() {
        //todo : waiting for AddSongForm
        //startActivity(Intent(this, TodoFormActivity::class.java))
    }

    private fun openDialogAbout() {
        //todo : waiting for AboutDialogFragment
        //AboutDialogFragment().show(supportFragmentManager, null)
    }
}