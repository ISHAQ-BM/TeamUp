package com.example.teamup.auth.presentation.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.teamup.R
import com.example.teamup.auth.presentation.ui.event.SignUpEvent
import com.example.teamup.auth.presentation.viewmodel.SignUpViewModel
import com.example.teamup.databinding.FragmentSignupBinding
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
    private var binding: FragmentSignupBinding? = null

    private val signUpWithGoogleLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credential = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credential.googleIdToken!!
                viewModel.onEvent(SignUpEvent.GoogleIdTokenChanged(googleIdToken))
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "error",Toast.LENGTH_SHORT).show()
            }

        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.email?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(SignUpEvent.EmailChanged(binding?.email?.text.toString()))
                showEmailErrorMessage(viewModel.uiState.value.emailError)
            }else {
                clearEmailError()
            }
        }

        binding?.fullName?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(SignUpEvent.FullNameChanged(binding?.fullName?.text.toString()))
                showFullNameErrorMessage(viewModel.uiState.value.fullNameError)
            }else {
                clearFullNameError()
            }
        }

        binding?.password?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(SignUpEvent.PasswordChanged(binding?.password?.text.toString()))
                showPasswordErrorMessage(viewModel.uiState.value.passwordError)
            }else {
                clearPasswordError()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateLoadingIndicatorVisibility(uiState.isLoading)
                    handleSignUpSuccess(uiState.isSignUpSuccessful)
                    uiState.googleSignInResult?.let { launch(it) }
                }
            }
        }

        binding?.google?.setOnClickListener {
            viewModel.onEvent(SignUpEvent.SignUpWithGoogleClicked)
        }

        binding?.signup?.setOnClickListener {
            clearInputFieldFocus()
            viewModel.onEvent(SignUpEvent.SignUpClicked)
        }

        binding?.login?.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        signUpWithGoogleLauncher.launch(intent)
    }

    private fun showEmailErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutEmail?.error = errorMessage
    }
    private fun showPasswordErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutPassword?.error = errorMessage
    }

    private fun showFullNameErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutFullName?.error = errorMessage
    }

    private fun clearEmailError() {
        binding?.inputLayoutEmail?.error = null
        binding?.inputLayoutEmail?.isErrorEnabled = false
    }

    private fun clearPasswordError() {
        binding?.inputLayoutPassword?.error = null
        binding?.inputLayoutPassword?.isErrorEnabled = false
    }
    private fun clearFullNameError() {
        binding?.inputLayoutFullName?.error = null
        binding?.inputLayoutFullName?.isErrorEnabled = false
    }

    private fun handleSignUpSuccess(isSignUpSuccess: Boolean) {
        if (isSignUpSuccess){
            viewModel.resetState()
            val action=SignUpFragmentDirections.actionSignUpFragmentToEmailConfirmationFragment(viewModel.uiState.value.email)
            findNavController().navigate(action)
        }
    }

    private fun clearInputFieldFocus() {
        binding?.apply {
            inputLayoutFullName.clearFocus()
            inputLayoutEmail.clearFocus()
            inputLayoutPassword.clearFocus()
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