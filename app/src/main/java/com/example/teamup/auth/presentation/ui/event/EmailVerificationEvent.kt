package com.example.teamup.auth.presentation.ui.event

sealed class EmailVerificationEvent {
    data object NextClicked:EmailVerificationEvent()

    data class EmailChanged(val email:String):EmailVerificationEvent()
    data class CodeChanged(val code:String):EmailVerificationEvent()
}