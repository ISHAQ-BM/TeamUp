package com.example.teamup.auth.presentation.ui.state

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class EmailConfirmationUiState (
    val email: String ="",
    val code: String = "",
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isConfirmSuccessful: Boolean = false,
    val isResendCodeSuccessful:Boolean=false
)