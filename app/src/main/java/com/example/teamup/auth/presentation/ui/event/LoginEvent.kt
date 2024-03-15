package com.example.teamup.auth.presentation.ui.event

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()

    data class ForgotPasswordClicked(val email: String) : LoginEvent()
    data object LoginClicked : LoginEvent()

    data object RegisterClicked : LoginEvent()

    data object LoginWithGoogleClicked : LoginEvent()

    data object LoginWithGithubClicked : LoginEvent()
}