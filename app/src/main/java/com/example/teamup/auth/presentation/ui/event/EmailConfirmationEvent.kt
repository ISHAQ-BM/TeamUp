package com.example.teamup.auth.presentation.ui.event

sealed class EmailConfirmationEvent {
    data class CodeChanged(val otp:String):EmailConfirmationEvent()
    data object VerifyClicked :EmailConfirmationEvent()

    data object ResendCodeClicked:EmailConfirmationEvent()
}