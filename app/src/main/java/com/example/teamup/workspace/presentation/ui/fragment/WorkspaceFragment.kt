package com.example.teamup.workspace.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamup.databinding.FragmentWorkspaceBinding


class WorkspaceFragment : Fragment() {
    private var binding: FragmentWorkspaceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkspaceBinding.inflate(inflater, container, false)
        return binding?.root
    }

}