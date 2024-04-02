package com.example.teamup.auth.presentation.ui.state

data class SecondOnboardingQuestionUiState(
    val questionText: String="",
    val selectedChoiceIndex: Int = -1,
    val selectedAnswer: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null
)
