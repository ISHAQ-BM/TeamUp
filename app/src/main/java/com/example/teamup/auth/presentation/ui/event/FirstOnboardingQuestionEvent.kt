package com.example.teamup.auth.presentation.ui.event

sealed class FirstOnboardingQuestionEvent {

    data object NextClicked : FirstOnboardingQuestionEvent()

    data class SelectedAnswerChanged(
                                     val selectedAnswer :String,
                                     val selectedChoiceIndex:Int) : FirstOnboardingQuestionEvent()
}