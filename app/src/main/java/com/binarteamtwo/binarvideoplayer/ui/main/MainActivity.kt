package com.binarteamtwo.binarvideoplayer.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.preference.UserPreference
import com.binarteamtwo.binarvideoplayer.databinding.ActivityMainBinding
import com.binarteamtwo.binarvideoplayer.ui.about.AboutDialogFragment
import com.binarteamtwo.binarvideoplayer.ui.addnewsong.AddNewSongActivity
import com.binarteamtwo.binarvideoplayer.ui.fragments.MediaPlaylistFragment
import com.binarteamtwo.binarvideoplayer.utils.views.ViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)
        showGreetings()
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_song -> {
                navigateToAddSongForm()
                true
            }
            R.id.menu_about -> {
                openDialogAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(MediaPlaylistFragment.newInstance(false), getString(R.string.main_playlist))
        fragmentAdapter.addFragment(MediaPlaylistFragment.newInstance(true), getString(R.string.main_favorite))
        binding.viewPager.apply {
            adapter = fragmentAdapter
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager, true) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }

    private fun navigateToAddSongForm() {

        AddNewSongActivity.startActivity(this,AddNewSongActivity.MODE_INSERT)
    }

    private fun openDialogAbout() {

        AboutDialogFragment().show(supportFragmentManager, null)
    }

    private fun showGreetings() {
        val snackBar = Snackbar.make(
            binding.root,
            String.format(getString(R.string.main_snackbar_greeting, UserPreference(this).userName)),
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.setAction(getString(R.string.main_snackbar_dismiss)) {
            snackBar.dismiss()
        }
        snackBar.show()
    }


    override fun onBackPressed() {
        super.onBackPressed()


    }
}