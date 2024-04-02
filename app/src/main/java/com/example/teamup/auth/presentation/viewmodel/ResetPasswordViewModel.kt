package com.example.teamup.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.ResetPasswordEvent
import com.example.teamup.auth.presentation.ui.state.ResetPasswordUiState
import com.example.teamup.core.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
):ViewModel(){
    private val _uiState = MutableStateFlow(ResetPasswordUiState())
    val uiState: StateFlow<ResetPasswordUiState> = _uiState

    fun onEvent(event:ResetPasswordEvent){
        when(event){
            is ResetPasswordEvent.ConfirmationPasswordChanged -> {
                _uiState.update { it.copy(confirmationPassword = event.confirmPassword) }
                validateConfirmationPassword()
            }
            is ResetPasswordEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
                validatePassword()
            }
            is ResetPasswordEvent.ResetPasswordClicked -> {
                if (isUserInputsValid())
                    resetPassword()

            }

            is ResetPasswordEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
            }
            is ResetPasswordEvent.TokenChanged -> {
                _uiState.update { it.copy(token = event.token) }
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

    private fun resetPassword(){
        viewModelScope.launch {
            authUseCase.resetPasswordUseCase(
                _uiState.value.email,
                _uiState.value.token,
                _uiState.value.password).collect{result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        Log.d("error","viewModel success")
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isResetPasswordSuccessful = true
                            )
                        }
                        Log.d("error","viewModel ${_uiState.value.isResetPasswordSuccessful}")
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

    private fun validateConfirmationPassword(): Boolean {
        val confirmationPasswordValidationResult = authUseCase.validateConfirmationPasswordUseCase(_uiState.value.password,_uiState.value.confirmationPassword)
        val hasError = !confirmationPasswordValidationResult.successful
        if (hasError) {
            _uiState.update { it.copy(confirmationPasswordError = confirmationPasswordValidationResult.errorMessage) }
            return false
        }
        _uiState.update { it.copy(confirmationPasswordError = null) }
        return true
    }

    private fun isUserInputsValid(): Boolean {
        return  validatePassword() && validateConfirmationPassword()
    }
}