package com.binarteamtwo.binarvideoplayer.ui.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.binarteamtwo.binarvideoplayer.ui.intro.form.FormFragment
import com.binarteamtwo.binarvideoplayer.ui.intro.form.FormFragmentListener
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.ActivityIntroBinding
import com.binarteamtwo.binarvideoplayer.utils.views.ViewPagerAdapter

class IntroActivity : AppCompatActivity(), FormFragmentListener {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bind()
        initViewPager()
    }

    private fun bind() {
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(
            IntroFragment.newInstance(
                "Welcome TO \n YOUR OWN MUSIC VIDEO PARADISE",
                R.drawable.ic_logo
            ), "Welcome"
        )
        fragmentAdapter.addFragment(
            IntroFragment.newInstance(
                "SUBMIT TO PERSONALISE \n AND ENJOY YOUR MUSIC EXPERIENCE",
                R.drawable.ic_logo
            ), "submit"
        )
        fragmentAdapter.addFragment(
            FormFragment(), "form"
        )
        binding.vpIntro.apply {
            adapter = fragmentAdapter
        }
        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when {
                    position == 0 -> {
                        binding.btnNext.visibility = View.VISIBLE
                        binding.btnNext.isEnabled = true
                        binding.btnGetStarted.visibility = View.VISIBLE
                        binding.btnGetStarted.isEnabled = true
                        binding.viewBgNext.visibility = View.VISIBLE
                        binding.dotsIndicator.visibility = View.VISIBLE
                    }
                    position < fragmentAdapter.itemCount - 1 -> {
                        binding.btnNext.visibility = View.VISIBLE
                        binding.btnNext.isEnabled = true
                        binding.btnGetStarted.visibility = View.INVISIBLE
                        binding.btnGetStarted.isEnabled = false
                        binding.viewBgNext.visibility = View.VISIBLE
                        binding.dotsIndicator.visibility = View.VISIBLE
                    }
                    position == fragmentAdapter.itemCount - 1 -> {
                        binding.btnNext.visibility = View.INVISIBLE
                        binding.btnNext.isEnabled = false
                        binding.btnGetStarted.visibility = View.INVISIBLE
                        binding.btnGetStarted.isEnabled = false
                        binding.viewBgNext.visibility = View.INVISIBLE
                        binding.dotsIndicator.visibility = View.INVISIBLE
                    }
                }
                super.onPageSelected(position)
            }
        })
        binding.dotsIndicator.setViewPager2(binding.vpIntro)

        binding.btnNext.setOnClickListener {
            if (getNextIndex() != -1) {
                binding.vpIntro.setCurrentItem(getNextIndex(), true)
            }
        }
        binding.btnGetStarted.setOnClickListener {
            binding.vpIntro.setCurrentItem(2, false)
        }
    }

    private fun getNextIndex(): Int {
        val maxPages = binding.vpIntro.adapter?.itemCount
        val currentPageIdx = binding.vpIntro.currentItem
        var selectedIdx = -1
        maxPages?.let {
            if (currentPageIdx + 1 < maxPages) {
                selectedIdx = currentPageIdx + 1
            }
        }
        return selectedIdx
    }

    override fun onNameSubmitted(text: String) {
    }
}