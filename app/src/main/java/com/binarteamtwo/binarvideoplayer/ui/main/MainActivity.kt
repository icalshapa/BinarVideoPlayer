package com.binarteamtwo.binarvideoplayer.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.local.sharedpreference.SessionPreferences
import com.binarteamtwo.binarvideoplayer.data.preference.UserPreference
import com.binarteamtwo.binarvideoplayer.databinding.ActivityMainBinding
import com.binarteamtwo.binarvideoplayer.ui.about.AboutDialogFragment
import com.binarteamtwo.binarvideoplayer.ui.favourite.FavouriteFragment
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageFragment
import com.binarteamtwo.binarvideoplayer.ui.login.LoginActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var homepageFragment = HomepageFragment()
    private var favouriteFragment = FavouriteFragment()
    private var activeFragment : Fragment = homepageFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //showGreetings()
        /*initViewPager()*/
        setupFragment()
    }

    private fun setupFragment() {
              for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_fragment_homepage, homepageFragment, Constant.TAG_FRAGMENT_HOME_PAGE)
            add(R.id.fl_fragment_homepage,favouriteFragment, Constant.TAG_FRAGMENT_FAVOURITE).hide(
                favouriteFragment)
        }.commit()
        // set title for first fragment
        supportActionBar?.title = "Latest Movie"

        // set click menu for changing fragment
        binding.bottomNavViewHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_latest_movies -> {

                    showFragment(homepageFragment)
                    true
                }
                else -> {

                    showFragment(favouriteFragment)
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
        activeFragment = fragment

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                showDialogLogout()
            }
            R.id.menu_logout -> {
                deleteLoginData()
                navigateToIntro()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }



    private fun openDialogAbout() {

        AboutDialogFragment().show(supportFragmentManager, null)
    }

    /*private fun showGreetings() {

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
    }*/

    private fun navigateToIntro(){
        val intent = Intent(this, LoginActivity::class.java)
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
}