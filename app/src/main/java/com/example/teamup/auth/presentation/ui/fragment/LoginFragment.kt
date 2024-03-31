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
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.teamup.R
import com.example.teamup.auth.presentation.ui.event.LoginEvent
import com.example.teamup.auth.presentation.ui.state.LoginUiState
import com.example.teamup.auth.presentation.viewmodel.LoginViewModel
import com.example.teamup.core.model.Resource
import com.example.teamup.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {

   private val viewModel: LoginViewModel by viewModels()

    private var binding: FragmentLoginBinding? = null

    private val loginWithGoogleLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credential = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credential.googleIdToken
                Toast.makeText(requireContext(), "successssss google",Toast.LENGTH_SHORT).show()

            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "error",Toast.LENGTH_SHORT).show()
            }

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.email?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(LoginEvent.EmailChanged(binding?.email?.text.toString()))
                showEmailErrorMessage(viewModel.uiState.value.emailError)
            }else {
                clearEmailError()
            }
        }

        binding?.password?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.onEvent(LoginEvent.PasswordChanged(binding?.password?.text.toString()))
                showPasswordErrorMessage(viewModel.uiState.value.passwordError)
            }else {
                clearPasswordError()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateLoadingIndicatorVisibility(uiState.isLoading)
                    handleLoginSuccess(uiState.isLoginSuccessful)
                    uiState.googleSignInResult?.let { launch(it) }
                }
            }
        }

        binding?.google?.setOnClickListener {
            viewModel.onEvent(LoginEvent.LoginWithGoogleClicked)
        }

        binding?.forgotPassword?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }

        binding?.signup?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding?.login?.setOnClickListener {
            clearInputFieldFocus();
            viewModel.onEvent(LoginEvent.LoginClicked)
        }









    }

    private fun clearInputFieldFocus() {
        binding?.apply {
            inputLayoutEmail.clearFocus()
            inputLayoutPassword.clearFocus()
        }
    }


    private fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        loginWithGoogleLauncher.launch(intent)
    }


    private fun showEmailErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutEmail?.error = errorMessage
    }
    private fun showPasswordErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty())
            binding?.inputLayoutPassword?.error = errorMessage
    }

    private fun clearEmailError() {
        binding?.inputLayoutEmail?.error = null
        binding?.inputLayoutEmail?.isErrorEnabled = false
    }

    private fun clearPasswordError() {
        binding?.inputLayoutPassword?.error = null
        binding?.inputLayoutPassword?.isErrorEnabled = false
    }

    private fun handleLoginSuccess(isLoginSuccess: Boolean) {
        if (isLoginSuccess)
            Toast.makeText(requireContext(),"success",Toast.LENGTH_LONG).show()
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