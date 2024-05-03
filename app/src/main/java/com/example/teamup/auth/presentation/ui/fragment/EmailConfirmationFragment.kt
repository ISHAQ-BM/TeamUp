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
import com.example.teamup.R
import com.example.teamup.auth.presentation.ui.event.EmailConfirmationEvent
import com.example.teamup.auth.presentation.viewmodel.EmailConfirmationViewModel
import com.example.teamup.databinding.FragmentEmailConfirmationBinding
import com.otpview.OTPListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmailConfirmationFragment : Fragment() {


    private var binding:FragmentEmailConfirmationBinding?=null
    private val viewModel :EmailConfirmationViewModel by viewModels()
    private val args: EmailConfirmationFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEmailConfirmationBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.onEvent(EmailConfirmationEvent.EmailChanged(args.email))

        binding?.tvSentCode?.text=getString(R.string.we_ve_sent_a_code,args.email)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateLoadingIndicatorVisibility(uiState.isLoading)
                    handleConfirmSuccess(uiState.isConfirmSuccessful)
                }
            }
        }

        binding?.code?.requestFocusOTP()


        binding?.code?.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }


            override fun onOTPComplete(otp: String) {
                viewModel.onEvent(EmailConfirmationEvent.CodeChanged(otp))

            }
        }

        binding?.verify?.setOnClickListener {
            viewModel.onEvent(EmailConfirmationEvent.VerifyClicked)
        }

        binding?.resendCode?.setOnClickListener {
            viewModel.onEvent(EmailConfirmationEvent.ResendCodeClicked)
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

    private fun handleConfirmSuccess(isConfirmSuccess: Boolean) {
        if (isConfirmSuccess)
            findNavController().navigate(R.id.action_emailConfirmationFragment_to_onboardingFragment1)
    }


}