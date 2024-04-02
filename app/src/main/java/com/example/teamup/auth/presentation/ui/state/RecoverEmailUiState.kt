package com.example.teamup.auth.presentation.ui.state

data class RecoverEmailUiState (
    val email:String="",
    val emailError: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isRecoverSuccessful: Boolean = false,

)