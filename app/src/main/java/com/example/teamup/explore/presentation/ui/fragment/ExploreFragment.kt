package com.example.teamup.explore.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamup.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {
    private var binding: FragmentExploreBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentExploreBinding.inflate(inflater,container,false)
        return binding?.root
    }

}