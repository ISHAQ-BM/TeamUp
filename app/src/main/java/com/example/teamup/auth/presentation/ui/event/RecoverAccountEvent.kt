package com.example.teamup.auth.presentation.ui.event

sealed class RecoverAccountEvent {
    data class EmailChanged(val email:String):RecoverAccountEvent()
    data object NextClicked :RecoverAccountEvent()
}