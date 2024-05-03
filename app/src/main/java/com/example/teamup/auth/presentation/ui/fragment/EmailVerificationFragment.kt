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
import androidx.navigation.fragment.navArgs
import com.example.teamup.auth.presentation.ui.event.EmailVerificationEvent
import com.example.teamup.auth.presentation.viewmodel.EmailVerificationViewModel
import com.example.teamup.databinding.FragmentEmailVerificationBinding
import com.otpview.OTPListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EmailVerificationFragment : Fragment() {


    private var binding: FragmentEmailVerificationBinding? = null
    private val viewModel:EmailVerificationViewModel by viewModels()
    private val args: EmailVerificationFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentEmailVerificationBinding.inflate(inflater,container,false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.onEvent(EmailVerificationEvent.EmailChanged(args.email))

        binding?.code?.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }


            override fun onOTPComplete(otp: String) {

                viewModel.onEvent(EmailVerificationEvent.CodeChanged(otp))
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateLoadingIndicatorVisibility(uiState.isLoading)
                    handleVerifySuccess(uiState.isVerifySuccessful)
                }
            }
        }

        binding?.next?.setOnClickListener {
            viewModel.onEvent(EmailVerificationEvent.NextClicked)
        }

        binding?.resendCode?.setOnClickListener {
            viewModel.onEvent(EmailVerificationEvent.ResendCodeClicked)
        }

    }

    private fun handleVerifySuccess(isVerifySuccess: Boolean) {
        if (isVerifySuccess){
            val action=EmailVerificationFragmentDirections.actionEmailVerificationFragmentToResetPasswordFragment(
                viewModel.uiState.value.token,
                viewModel.uiState.value.email)
            findNavController().navigate(action)
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