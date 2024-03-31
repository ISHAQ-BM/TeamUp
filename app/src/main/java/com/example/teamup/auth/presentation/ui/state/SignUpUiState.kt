package com.example.teamup.auth.presentation.ui.state

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class SignUpUiState(
    val fullName:String="",
    val fullNameError:String?=null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isSignUpSuccessful: Boolean = false,
    val googleSignInResult: BeginSignInResult? = null,
    val googleIdToken:String?=null
)
