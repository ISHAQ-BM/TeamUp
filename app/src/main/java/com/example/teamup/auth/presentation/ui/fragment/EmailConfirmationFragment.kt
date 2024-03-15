package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamup.databinding.FragmentEmailConfirmationBinding

class EmailConfirmationFragment : Fragment() {


    private var binding:FragmentEmailConfirmationBinding?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEmailConfirmationBinding.inflate(inflater,container,false)
        return binding?.root
       }


}