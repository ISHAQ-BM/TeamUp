package com.example.teamup.auth.presentation.ui.event

sealed class SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()


    data object SignUpClicked : SignUpEvent()
    data object LoginClicked : SignUpEvent()

    data object SignUpWithGoogleClicked : SignUpEvent()

    data object SignUpWithGithubClicked : SignUpEvent()
}