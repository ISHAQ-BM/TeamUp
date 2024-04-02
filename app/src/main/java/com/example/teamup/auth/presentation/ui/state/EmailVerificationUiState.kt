package com.example.teamup.auth.presentation.ui.state

data class EmailVerificationUiState(
    val email: String ="",
    val code: String = "",
    val token:String="",
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isVerifySuccessful: Boolean = false,
    val isResendCodeSuccessful:Boolean=false
)
