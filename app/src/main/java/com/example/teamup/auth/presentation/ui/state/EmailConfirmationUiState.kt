package com.example.teamup.auth.presentation.ui.state

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class EmailConfirmationUiState (
    val code: String = "",
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isVerifySuccessful: Boolean = false,
)