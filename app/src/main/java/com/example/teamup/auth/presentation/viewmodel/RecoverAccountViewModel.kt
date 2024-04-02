package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.RecoverAccountEvent
import com.example.teamup.auth.presentation.ui.state.RecoverEmailUiState
import com.example.teamup.core.model.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecoverAccountViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
):ViewModel() {

    private val _uiState = MutableStateFlow(RecoverEmailUiState())
    val uiState: StateFlow<RecoverEmailUiState> = _uiState


    fun onEvent(event: RecoverAccountEvent){
        when(event){
            is RecoverAccountEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
                validateEmail()
            }
            is RecoverAccountEvent.NextClicked -> {
                if (validateEmail())
                    initiatePasswordReset()
            }
        }
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

    private fun initiatePasswordReset() {
        viewModelScope.launch {
            authUseCase.forgotPasswordUseCase(_uiState.value.email).collect{result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isRecoverSuccessful = true
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
}