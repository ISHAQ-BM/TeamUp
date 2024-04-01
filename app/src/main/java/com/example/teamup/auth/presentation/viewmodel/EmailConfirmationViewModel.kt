package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teamup.auth.presentation.ui.event.EmailConfirmationEvent
import com.example.teamup.auth.presentation.ui.state.EmailConfirmationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EmailConfirmationViewModel :ViewModel() {
    private val _uiState =MutableStateFlow(EmailConfirmationUiState())
    val uiState:StateFlow<EmailConfirmationUiState> =_uiState



    fun onEvent(event: EmailConfirmationEvent){
        when(event){
            is EmailConfirmationEvent.CodeChanged ->{

            }

            is EmailConfirmationEvent.VerifyClicked ->{

            }

            is EmailConfirmationEvent.ResendCodeClicked ->{

            }
        }
    }
}