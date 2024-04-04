package com.example.teamup.auth.presentation.ui.event

sealed class EmailConfirmationEvent {
    data class CodeChanged(val code:String):EmailConfirmationEvent()
    data object VerifyClicked :EmailConfirmationEvent()

    data class EmailChanged(val email:String):EmailConfirmationEvent()

    data object ResendCodeClicked:EmailConfirmationEvent()
}