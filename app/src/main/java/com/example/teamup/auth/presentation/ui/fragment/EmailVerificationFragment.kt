package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamup.R
import com.example.teamup.databinding.FragmentEmailVerificationBinding


class EmailVerificationFragment : Fragment() {


    private var binding: FragmentEmailVerificationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentEmailVerificationBinding.inflate(inflater,container,false)
        return binding?.root

    }


}