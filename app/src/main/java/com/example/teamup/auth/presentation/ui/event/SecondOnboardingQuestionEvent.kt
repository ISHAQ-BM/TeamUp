package com.example.teamup.auth.presentation.ui.event

sealed class SecondOnboardingQuestionEvent {
    data object NextClicked : SecondOnboardingQuestionEvent()

    data class SelectedAnswerChanged(
        val selectedAnswer :String,
        val selectedChoiceIndex:Int) : SecondOnboardingQuestionEvent()
}