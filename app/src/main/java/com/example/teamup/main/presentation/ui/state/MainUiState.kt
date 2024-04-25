package com.example.teamup.main.presentation.ui.state

data class MainUiState(
    val isUserAuthenticated: Boolean = false,
    val isEmailVerified: Boolean? = false
)