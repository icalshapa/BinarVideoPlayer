package com.binarteamtwo.binarvideoplayer.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.local.sharedpreference.SessionPreferences
import com.binarteamtwo.binarvideoplayer.data.preference.UserPreference
import com.binarteamtwo.binarvideoplayer.databinding.ActivityMainBinding
import com.binarteamtwo.binarvideoplayer.ui.about.AboutDialogFragment
import com.binarteamtwo.binarvideoplayer.ui.favourite.FavouriteFragment
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageFragment
import com.binarteamtwo.binarvideoplayer.ui.intro.IntroActivity
import com.binarteamtwo.binarvideoplayer.ui.intro.IntroFragment.Companion.newInstance
import com.binarteamtwo.binarvideoplayer.utils.Constants
import com.binarteamtwo.binarvideoplayer.utils.views.ViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var homepageFragment = HomepageFragment()
    private var favouriteFragment = FavouriteFragment()
    private var activeFragment = homepageFragment
    /*private val TAG = MainActivity::class.java.simpleName*/


    override fun onCreate(savedInstanceState: Bundle?) {
        /*Log.d(TAG, "onCreate: ")*/
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showGreetings()
        initViewPager()
        /*setupFragment()*/
    }

    /*private fun setupFragment() {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_fragment_homepage, homepageFragment, Constants.TAG_FRAGMENT_HOME_PAGE)
            add(R.id.fl_fragment_homepage, favouriteFragment, Constants.TAG_FRAGMENT_FAVOURITE)
        }.commit()
        // set title for first fragment
        supportActionBar?.title = "Top Movies"

        // set click menu for changing fragment
        binding.bottomNavViewHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_top_movies -> {
                    supportActionBar?.title = "Top Movies"
                    showFragment(HomepageFragment())
                    true
                }
                else -> {
                    supportActionBar?.title = "Favourite"
                    *//*showFragment(FavouriteFragment())*//*
                    true
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment as HomepageFragment
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                showDialogLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(HomepageFragment.newInstance(false),
            getString(R.string.main_playlist)
        )
        fragmentAdapter.addFragment(
            HomepageFragment.newInstance(true),
            getString(R.string.main_favorite)
        )
        binding.viewPager.apply {
            adapter = fragmentAdapter
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager, true) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }

/*private fun navigateToAddSongForm() {

    AddNewSongActivity.startActivity(this,AddNewSongActivity.MODE_INSERT)
}*/

    private fun openDialogAbout() {

        AboutDialogFragment().show(supportFragmentManager, null)
    }

    private fun showGreetings() {

        val snackBar = Snackbar.make(
            binding.root,
            String.format(
                getString(
                    R.string.main_snackbar_greeting,
                    UserPreference(this).userName
                )
            ),
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.setAction(getString(R.string.main_snackbar_dismiss)) {
            snackBar.dismiss()
        }
        snackBar.view.setBackgroundColor(Color.parseColor("#000000"))
        val textView = snackBar.view.findViewById(R.id.snackbar_text) as TextView
        // change Snackbar text color
        textView.setTextColor(Color.parseColor("#FFFFFF"))
        snackBar.show()
    }

    private fun navigateToIntro() {
        val intent = Intent(this, IntroActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun deleteLoginData() {
        UserPreference(this).isUserLoggedIn = false
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun showDialogLogout() {
        val alertDialog = AlertDialog.Builder(this)
            .apply {
                setTitle("Do you want to Logout")
                setPositiveButton("Yes") { dialog, id ->
                    logout()
                    dialog.dismiss()
                }
                setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            }.create().show()

    }

    private fun logout() {
        deleteSession()
        /*navigateToLogin()*/
    }

    private fun deleteSession() {
        SessionPreferences(this).deleteSession()
    }

    /*private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }*/
}