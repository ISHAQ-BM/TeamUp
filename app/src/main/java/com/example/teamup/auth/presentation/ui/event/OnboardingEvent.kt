package com.example.teamup.auth.presentation.ui.event

sealed class OnboardingEvent {
    data class UserChoiceChanged(val userChoice:String):OnboardingEvent()
}