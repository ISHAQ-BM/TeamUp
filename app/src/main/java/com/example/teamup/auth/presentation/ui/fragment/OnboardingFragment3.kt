package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamup.databinding.FragmentOnboarding3Binding

class OnboardingFragment3 : Fragment() {
    private var binding: FragmentOnboarding3Binding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOnboarding3Binding.inflate(inflater,container,false)
        return binding?.root

    }
}