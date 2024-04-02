package com.example.teamup.auth.presentation.ui.state

data class ResetPasswordUiState(
    val email: String ="",
    val token:String="",
    val password: String = "",
    val passwordError: String? = null,
    val confirmationPassword: String = "",
    val confirmationPasswordError: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isResetPasswordSuccessful:Boolean=false
)
