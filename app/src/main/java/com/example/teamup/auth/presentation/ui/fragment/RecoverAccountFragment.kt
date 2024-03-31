package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamup.databinding.FragmentRecoverAccountBinding

class RecoverAccountFragment : Fragment() {
    private var binding: FragmentRecoverAccountBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding?.root

    }
}