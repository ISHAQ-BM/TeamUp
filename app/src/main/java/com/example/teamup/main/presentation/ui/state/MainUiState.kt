package com.example.teamup.main.presentation.ui.state

data class MainUiState(
    val isUserLoggedIn: Boolean = false,
    val isEmailConfirmed: Boolean? = false
)