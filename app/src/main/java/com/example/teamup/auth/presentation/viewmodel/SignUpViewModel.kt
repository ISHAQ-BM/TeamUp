package com.example.teamup.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.SignUpEvent
import com.example.teamup.auth.presentation.ui.state.SignUpUiState
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.SignInClient

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    val oneTapClient: SignInClient
):ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState


    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.FullNameChanged ->{
                _uiState.update { it.copy(fullName = event.fullName) }
                validateFullName()
            }
            is SignUpEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
                validateEmail()
            }
            is SignUpEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
                validatePassword()
            }
            is SignUpEvent.SignUpWithGoogleClicked -> {
                initiateGoogleOneTapFlow()
            }
            is SignUpEvent.SignUpWithGithubClicked -> {

            }
            is SignUpEvent.SignUpClicked-> {
                if (isUserInputsValid()){
                    Log.d("error","input  valide")
                    signUpWithEmailAndPassword()
                }

            }

            is SignUpEvent.GoogleIdTokenChanged -> {
                _uiState.update { it.copy(googleIdToken = event.googleIdToken) }
                signUserWithGoogle()
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
                                isSignUpSuccessful = true
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

    private fun signUpWithEmailAndPassword() {

        viewModelScope.launch {

            authUseCase.signUpWithEmailAndPasswordUseCase(
                _uiState.value.fullName,
                _uiState.value.email,
                _uiState.value.password
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        loginWithEmailAndPassword()
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isSignUpSuccessful = false,
                                generalErrorMessage = result.message
                            )
                        }
                    }
                }
            }
        }


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
                                isSignUpSuccessful = true
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isSignUpSuccessful = false,
                                generalErrorMessage = result.message
                            )
                        }
                    }
                }
            }
        }


    }

    private fun validateFullName():Boolean {
        val fullNameValidationResult = authUseCase.validateFullNameUseCase(_uiState.value.fullName)
        val hasError = !fullNameValidationResult.successful
        if (hasError) {
            _uiState.update { it.copy(fullNameError = fullNameValidationResult.errorMessage) }
            return false
        }
        _uiState.update { it.copy(fullNameError = null) }
        return true
    }





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

    private fun isUserInputsValid(): Boolean {
        return validateEmail() && validatePassword() && validateFullName()
    }

}