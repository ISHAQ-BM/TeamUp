package com.example.teamup.auth.presentation.ui.event

sealed class ThirdOnboardingQuestionEvent {
    data object NextClicked : ThirdOnboardingQuestionEvent()

    data class SelectedAnswerChanged(
        val selectedAnswer :String,
        val selectedChoiceIndex:Int) : ThirdOnboardingQuestionEvent()
}