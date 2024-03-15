package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.LoginEvent
import com.example.teamup.auth.presentation.ui.state.LoginUiState
import com.example.teamup.core.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState: LiveData<LoginUiState> = _uiState


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _uiState.value =_uiState.value?.copy(email = event.email)
            }

            is LoginEvent.PasswordChanged -> {
                _uiState.value = _uiState.value?.copy(password = event.password)
            }

            is LoginEvent.ForgotPasswordClicked -> {
                if (isEmailValid()){
                    sendResetPasswordEmail()
                }

            }

            is LoginEvent.LoginWithGoogleClicked -> {
                loginWithGoogle()
            }

            is LoginEvent.LoginWithGithubClicked -> {

            }

            is LoginEvent.LoginClicked -> {
                if (isUserInputsValid())
                    login()
            }

            is LoginEvent.RegisterClicked -> {}
        }
    }

    private fun loginWithGoogle() {
        _uiState.value = _uiState.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {
            authUseCase.signUserWithOneTapUseCase().also {
                when(it){
                    is Resource.Success ->{
                        _uiState.value=_uiState.value?.copy(
                            isLoading = false,
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value=_uiState.value!!.copy(
                            isLoading = false,
                        )
                    }


                }
            }
        }
    }

    private fun sendResetPasswordEmail() {
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
                    }
                    is Resource.Error -> {
                        _uiState.value=_uiState.value!!.copy(
                            isLoading = false
                        )
                    }

                }
            }
        }
    }

    private fun isEmailValid(): Boolean {
        val emailValidationResult =
            _uiState.value?.let { authUseCase.validateEmailUseCase(it.email) }
        val hasError =
            emailValidationResult?.successful == false
        if (hasError) {
            _uiState.value = _uiState.value!!.copy(
                emailError = emailValidationResult?.errorMessage
            )
            return false
        }
        return true


    }

    private fun isUserInputsValid():Boolean {
        val emailValidationResult =
            _uiState.value?.let { authUseCase.validateEmailUseCase(it.email) }
        val passwordValidationResult =
            _uiState.value?.let { authUseCase.validatePasswordUseCase(it.password) }

        val hasError =
            emailValidationResult?.successful == false && passwordValidationResult?.successful == false

        if (hasError) {
            _uiState.value = _uiState.value!!.copy(
                emailError = emailValidationResult?.errorMessage,
                passwordError = passwordValidationResult?.errorMessage
            )
            return false
        }
        return true

    }

    private fun login() {
        _uiState.value = _uiState.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {

            authUseCase.logInWithEmailAndPasswordUseCase(
                _uiState.value?.email!!,
                _uiState.value?.password!!
            ).also {
                when(it){
                    is Resource.Success ->{
                        _uiState.value=_uiState.value?.copy(
                            isLoading = false,
                            loginSuccess = true
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value=_uiState.value!!.copy(
                            isLoading = false
                        )
                    }
                    else ->{}


                }
            }
        }


    }

}