package com.example.teamup.workspace.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamup.databinding.FragmentExploreBinding


class WorkspaceFragment : Fragment() {
    private var binding: FragmentExploreBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentExploreBinding.inflate(inflater,container,false)
        return binding?.root
    }

}