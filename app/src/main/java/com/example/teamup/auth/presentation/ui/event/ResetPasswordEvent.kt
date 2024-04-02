package com.example.teamup.auth.presentation.ui.event

sealed class ResetPasswordEvent {

    data class EmailChanged(val email:String):ResetPasswordEvent()
    data class TokenChanged(val token:String):ResetPasswordEvent()
    data class PasswordChanged(val password:String):ResetPasswordEvent()
    data class ConfirmationPasswordChanged(val confirmPassword:String):ResetPasswordEvent()

    data object ResetPasswordClicked :ResetPasswordEvent()
}