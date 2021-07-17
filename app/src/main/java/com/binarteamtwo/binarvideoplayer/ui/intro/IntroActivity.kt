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
                "Welcome TO YOUR \n OWN MUSIC VIDEO \n PARADISE",
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
                        binding.tvNext.visibility = View.VISIBLE
                        binding.tvNext.isEnabled = true
                        binding.tvSkip.visibility = View.VISIBLE
                        binding.tvSkip.isEnabled = true
                        binding.tvBack.visibility = View.INVISIBLE
                        binding.tvBack.isEnabled = false
                        binding.dotsIndicator.visibility = View.VISIBLE
                    }
                    position < fragmentAdapter.itemCount - 1 -> {
                        binding.tvNext.visibility = View.VISIBLE
                        binding.tvNext.isEnabled = true
                        binding.tvSkip.visibility = View.INVISIBLE
                        binding.tvSkip.isEnabled = false
                        binding.tvBack.visibility = View.VISIBLE
                        binding.tvBack.isEnabled = true
                        binding.dotsIndicator.visibility = View.VISIBLE
                    }
                    position == fragmentAdapter.itemCount - 1 -> {
                        binding.tvNext.visibility = View.INVISIBLE
                        binding.tvNext.isEnabled = false
                        binding.tvSkip.visibility = View.INVISIBLE
                        binding.tvSkip.isEnabled = false
                        binding.tvBack.visibility = View.INVISIBLE
                        binding.tvBack.isEnabled = false
                        binding.dotsIndicator.visibility = View.INVISIBLE
                    }
                }
                super.onPageSelected(position)
            }
        })
        binding.dotsIndicator.setViewPager2(binding.vpIntro)

        binding.tvNext.setOnClickListener {
            if (getNextIndex() != -1) {
                binding.vpIntro.setCurrentItem(getNextIndex(), true)
            }
        }
        binding.tvBack.setOnClickListener {
            if (getPreviousIndex() != -1) {
                binding.vpIntro.setCurrentItem(getPreviousIndex(), true)
            }
        }
        binding.tvSkip.setOnClickListener {
            binding.vpIntro.setCurrentItem(2, false)
        }
    }

    private fun getPreviousIndex(): Int {
        val currentPageIdx = binding.vpIntro.currentItem
        return if (currentPageIdx - 1 >= 0) {
            currentPageIdx - 1
        } else {
            -1
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