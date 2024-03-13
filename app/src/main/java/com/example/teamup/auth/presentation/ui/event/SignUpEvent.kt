package com.example.teamup.auth.presentation.ui.event

sealed class SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()


    data object SignUp : SignUpEvent()

    data object SignUpWithGoogle : SignUpEvent()

    data object SignUpWithGithub : SignUpEvent()
}