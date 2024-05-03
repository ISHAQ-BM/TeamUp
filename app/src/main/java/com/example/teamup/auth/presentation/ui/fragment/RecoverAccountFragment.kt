package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.teamup.auth.presentation.ui.event.RecoverAccountEvent
import com.example.teamup.auth.presentation.viewmodel.RecoverAccountViewModel
import com.example.teamup.databinding.FragmentRecoverAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecoverAccountFragment : Fragment() {
    private var binding: FragmentRecoverAccountBinding? = null
    private val viewModel:RecoverAccountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateLoadingIndicatorVisibility(uiState.isLoading)
                    handleRecoverAccountSuccess(uiState.isRecoverSuccessful)
                }
            }
        }


        binding?.email?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(RecoverAccountEvent.EmailChanged(binding?.email?.text.toString()))
                showEmailErrorMessage(viewModel.uiState.value.emailError)
            }else {
                clearEmailError()
            }
        }
        binding?.next?.setOnClickListener {
            clearInputFieldFocus()
            viewModel.onEvent(RecoverAccountEvent.NextClicked)
        }

    }

    private fun showEmailErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutEmail?.error = errorMessage
    }

    private fun clearEmailError() {
        binding?.inputLayoutEmail?.error = null
        binding?.inputLayoutEmail?.isErrorEnabled = false
    }

    private fun handleRecoverAccountSuccess(isRecoverSuccess: Boolean) {
        if (isRecoverSuccess){
            viewModel.resetState()
            val action=RecoverAccountFragmentDirections.actionRecoverAccountFragmentToEmailVerificationFragment(viewModel.uiState.value.email)
            findNavController().navigate(action)
        }

    }

    private fun clearInputFieldFocus() {
        binding?.apply {
            inputLayoutEmail.clearFocus()
        }
    }

    private fun updateLoadingIndicatorVisibility(isLoading: Boolean) {
        if (isLoading) {
            binding?.apply {
                progressIndicator.visibility = View.VISIBLE
                grayOverlay.visibility = View.VISIBLE
            }
        } else {
            binding?.apply {
                progressIndicator.visibility = View.GONE
                grayOverlay.visibility = View.GONE
            }
        }

    }
}