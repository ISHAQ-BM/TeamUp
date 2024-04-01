package com.example.teamup.auth.presentation.ui.event

sealed class EmailConfirmationEvent {
    data class CodeChanged(val code:String):EmailConfirmationEvent()
    data object VerifyClicked :EmailConfirmationEvent()

    data object ResendCodeClicked:EmailConfirmationEvent()
}