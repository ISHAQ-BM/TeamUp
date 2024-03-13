package com.example.teamup.auth.presentation.ui.event

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()

    data class ForgotPassword(val email: String) : LoginEvent()
    data object Login : LoginEvent()

    data object LoginWithGoogle : LoginEvent()

    data object LoginWithGithub : LoginEvent()
}