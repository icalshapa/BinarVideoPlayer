package com.binarteamtwo.binarvideoplayer.ui.intro.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binarteamtwo.binarvideoplayer.MainActivity
import com.binarteamtwo.binarvideoplayer.data.preference.UserPreference
import com.binarteamtwo.binarvideoplayer.databinding.FragmentFormActivityBinding
import com.google.android.material.snackbar.Snackbar

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormActivityBinding
    private lateinit var listener: FormFragmentListener
    private lateinit var userPreference: UserPreference
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormActivityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        prefilledCurrentAccount()

        binding.btnSubmit.setOnClickListener {
            if(this::listener.isInitialized){
                listener.onNameSubmitted(binding.etPlayerName.text.toString())
                navigateToMenuPlayer()
            }
        }
    }

    private fun prefilledCurrentAccount() {
        context?.let {
            userPreference = UserPreference(it)
            binding.etPlayerName.setText(userPreference.userName.orEmpty())
        }
    }

    private fun isFormFilled(): Boolean {
        val name = binding.etPlayerName.text.toString()
        var isFormValid = true

        when {
            name.isEmpty() -> {
                isFormValid = false
                Snackbar.make(binding.root, "Please fill your name", Snackbar.LENGTH_SHORT).show()
            }
        }
        return isFormValid
    }

    private fun navigateToMenuPlayer(){
        if(isFormFilled()){
            userPreference.userName = binding.etPlayerName.text.toString()
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FormFragmentListener){
            listener = context
        }
    }
}
