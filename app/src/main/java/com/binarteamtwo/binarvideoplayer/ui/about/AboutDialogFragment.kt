package com.binarteamtwo.binarvideoplayer.ui.about

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.databinding.FragmentAboutDialogBinding
import com.bumptech.glide.Glide

class AboutDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAboutDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        context?.let {
            Glide.with(it)
                .load(Constant.URL_PHOTO_PROFILE_FAISAL)
                .circleCrop()
                .into(binding.ivImageProfile1)
            Glide.with(it)
                .load(Constant.URL_PHOTO_PROFILE_IRFAN)
                .circleCrop()
                .into(binding.ivImageProfile2)
            Glide.with(it)
                .load(Constant.URL_PHOTO_PROFILE_FACHRUL)
                .circleCrop()
                .into(binding.ivImageProfile3)
            Glide.with(it)
                .load(Constant.URL_PHOTO_PROFILE_RIZQI)
                .circleCrop()
                .into(binding.ivImageProfile4)
            Glide.with(it)
                .load(Constant.URL_PHOTO_PROFILE_RENGGA)
                .circleCrop()
                .into(binding.ivImageProfile5)
        }
        binding.btnRedirectGithub1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constant.URL_GITHUB_FAISAL)
            startActivity(intent)
        }
        binding.btnRedirectGithub2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constant.URL_GITHUB_IRFAN)
            startActivity(intent)
        }
        binding.btnRedirectGithub3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constant.URL_GITHUB_FACHRUL)
            startActivity(intent)
        }
        binding.btnRedirectGithub4.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constant.URL_GITHUB_RIZQI)
            startActivity(intent)
        }
        binding.btnRedirectGithub5.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constant.URL_GITHUB_RENGGA)
            startActivity(intent)
        }

    }

}