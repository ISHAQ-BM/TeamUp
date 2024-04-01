package com.example.teamup.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.LoginEvent
import com.example.teamup.auth.presentation.ui.event.SignUpEvent
import com.example.teamup.auth.presentation.ui.state.LoginUiState
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    val oneTapClient: SignInClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
                validateEmail()
            }

            is LoginEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
                validatePassword()
            }

            is LoginEvent.GoogleIdTokenChanged -> {

                signUserWithGoogle()
            }

            is LoginEvent.LoginWithGoogleClicked -> {
                initiateGoogleOneTapFlow()

            }

            is LoginEvent.LoginWithGithubClicked -> {

            }

            is LoginEvent.LoginClicked -> {
                if (isUserInputsValid())
                    loginWithEmailAndPassword()

            }

        }
    }

    private fun signUserWithGoogle() {
        viewModelScope.launch {
            authUseCase.signUserWithGoogleUseCse(_uiState.value.googleIdToken).collect{result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = true
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                generalErrorMessage = result.message
                            )
                        }
                    }

                }
            }
        }
    }

    private fun validatePassword(): Boolean {
        val passwordValidationResult = authUseCase.validatePasswordUseCase(_uiState.value.password)
        val hasError = !passwordValidationResult.successful
        if (hasError) {
            _uiState.update { it.copy(passwordError = passwordValidationResult.errorMessage) }
            return false
        }
        _uiState.update { it.copy(passwordError = null) }
        return true
    }

    private fun initiateGoogleOneTapFlow() {
        viewModelScope.launch {
            authUseCase.initiateGoogleOneTapFlowUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                googleSignInResult = result.data
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                generalErrorMessage = result.message
                            )
                        }
                    }

                }
            }
        }
    }


    /*private fun sendResetPasswordEmail() {
        _uiState.value = _uiState.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {

            authUseCase.forgotPasswordUseCase(
                _uiState.value?.email!!,

                ).also {
                when(it){
                    is Resource.Success ->{
                        _uiState.value=_uiState.value?.copy(
                            isLoading = false,
                        )
                        Log.d("email sended","successs")
                    }
                    is Resource.Error -> {
                        _uiState.value=_uiState.value!!.copy(
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> TODO()
                }
            }
        }
    }
    */
    private fun validateEmail(): Boolean {
        val emailValidationResult = authUseCase.validateEmailUseCase(_uiState.value.email)
        val hasError = !emailValidationResult.successful
        if (hasError) {
            _uiState.update { it.copy(emailError = emailValidationResult.errorMessage) }
            return false
        }
        _uiState.update { it.copy(emailError = null) }
        return true
    }

    private fun isUserInputsValid(): Boolean {
        return validateEmail() && validatePassword()
    }

    private fun loginWithEmailAndPassword() {

        viewModelScope.launch {

            authUseCase.logInWithEmailAndPasswordUseCase(
                _uiState.value.email,
                _uiState.value.password
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = true
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = false,
                                generalErrorMessage = result.message
                            )
                        }
                    }
                }
            }
        }


    }


}