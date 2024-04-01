package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.EmailConfirmationEvent
import com.example.teamup.auth.presentation.ui.state.EmailConfirmationUiState
import com.example.teamup.core.model.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmailConfirmationViewModel @Inject constructor(
    val authUseCase: AuthUseCase
) :ViewModel() {
    private val _uiState =MutableStateFlow(EmailConfirmationUiState())
    val uiState:StateFlow<EmailConfirmationUiState> =_uiState



    fun onEvent(event: EmailConfirmationEvent){
        when(event){
            is EmailConfirmationEvent.CodeChanged ->{
                _uiState.update { it.copy(code = event.code) }
            }

            is EmailConfirmationEvent.VerifyClicked ->{
                confirmEmail()
            }

            is EmailConfirmationEvent.ResendCodeClicked ->{
                resendCode()
            }
        }
    }

    private fun resendCode() {
        viewModelScope.launch{
            authUseCase.sendVerificationEmailUseCase(_uiState.value.email).collect{ result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isResendCodeSuccessful = true
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

    private fun confirmEmail() {
        viewModelScope.launch{
            authUseCase.confirmEmailUseCase(_uiState.value.email,_uiState.value.code).collect{result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isConfirmSuccessful = true
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