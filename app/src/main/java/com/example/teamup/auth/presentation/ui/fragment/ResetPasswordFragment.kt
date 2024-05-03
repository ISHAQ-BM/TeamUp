package com.example.teamup.auth.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teamup.auth.presentation.ui.event.ResetPasswordEvent
import com.example.teamup.auth.presentation.viewmodel.ResetPasswordViewModel
import com.example.teamup.databinding.FragmentResetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {


    private var binding: FragmentResetPasswordBinding? = null
    private val viewModel:ResetPasswordViewModel by viewModels()
    private val args:ResetPasswordFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.onEvent(ResetPasswordEvent.EmailChanged(args.email))
        viewModel.onEvent(ResetPasswordEvent.TokenChanged(args.token))
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateLoadingIndicatorVisibility(uiState.isLoading)
                    handleResetPasswordSuccess(uiState.isResetPasswordSuccessful)
                }
            }
        }

        binding?.confirmationPassword?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(ResetPasswordEvent.ConfirmationPasswordChanged(binding?.confirmationPassword?.text.toString()))
                showConfirmationPasswordErrorMessage(viewModel.uiState.value.confirmationPasswordError)
            }else {
                clearConfirmationPasswordError()
            }
        }

        binding?.password?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(ResetPasswordEvent.PasswordChanged(binding?.password?.text.toString()))
                showPasswordErrorMessage(viewModel.uiState.value.passwordError)
            }else {
                clearPasswordError()
            }
        }

        binding?.resetPassword?.setOnClickListener {
            clearInputFieldFocus()
            viewModel.onEvent(ResetPasswordEvent.ResetPasswordClicked)
        }
    }

    private fun clearInputFieldFocus() {
        binding?.apply {
            inputLayoutPassword.clearFocus()
            inputLayoutConfirmationPassword.clearFocus()
        }
    }

    private fun showPasswordErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutPassword?.error = errorMessage
    }
    private fun showConfirmationPasswordErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutConfirmationPassword?.error = errorMessage
    }

    private fun clearPasswordError() {
        binding?.inputLayoutPassword?.error = null
        binding?.inputLayoutPassword?.isErrorEnabled = false
    }

    private fun clearConfirmationPasswordError() {
        binding?.inputLayoutConfirmationPassword?.error = null
        binding?.inputLayoutConfirmationPassword?.isErrorEnabled = false
    }

    private fun handleResetPasswordSuccess(isResetPasswordSuccess: Boolean) {
        if (isResetPasswordSuccess)
            Toast.makeText(requireContext(),"Your password has been reset successfully.", Toast.LENGTH_LONG).show()


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