package com.binarteamtwo.binarvideoplayer.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.binarteamtwo.binarvideoplayer.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    private var title: String? = null
    private var imgRes: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_PARAM_INTRO_TITLE)
            imgRes = it.getInt(ARG_PARAM_INTRO_IMG_RES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = title.orEmpty()
        context?.let {
            binding.ivIntro.setImageDrawable(ContextCompat.getDrawable(it, imgRes))
        }
    }

    companion object {
        private const val ARG_PARAM_INTRO_TITLE = "ARG_PARAM_INTRO_TITLE"
        private const val ARG_PARAM_INTRO_IMG_RES = "ARG_PARAM_INTRO_IMG_RES"

        @JvmStatic
        fun newInstance(title: String, imgRes: Int) =
            IntroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_INTRO_TITLE, title)
                    putInt(ARG_PARAM_INTRO_IMG_RES, imgRes)
                }
            }
    }
}