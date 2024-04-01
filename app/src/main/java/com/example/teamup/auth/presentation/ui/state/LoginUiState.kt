package com.example.teamup.auth.presentation.ui.state

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class LoginUiState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isLoginSuccessful: Boolean = false,
    val googleSignInResult: BeginSignInResult? = null,
    val googleIdToken:String=""


)
