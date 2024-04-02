package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.EmailVerificationEvent
import com.example.teamup.auth.presentation.ui.state.EmailVerificationUiState
import com.example.teamup.core.model.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmailVerificationViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
):ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerificationUiState())
    val uiState: StateFlow<EmailVerificationUiState> =_uiState

    fun onEvent(event: EmailVerificationEvent){
        when(event){
            is EmailVerificationEvent.CodeChanged -> {
                _uiState.update { it.copy(code = event.code) }
            }
            is EmailVerificationEvent.NextClicked -> {
                verifyEmail()
            }

            is EmailVerificationEvent.EmailChanged ->{
                _uiState.update { it.copy(email = event.email) }
            }
        }
    }

    private fun verifyEmail() {
        viewModelScope.launch {
            authUseCase.exchangeResetCodeUseCase(_uiState.value.email,_uiState.value.code).collect{ result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                token = result.data!!,
                                isLoading = false,
                                isVerifySuccessful = true,

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