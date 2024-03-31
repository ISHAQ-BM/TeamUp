package com.example.teamup.auth.presentation.ui.state

data class SignUpUiState(
    val displayName:String="",
    val displayNameError:String?=null,
    val email:String="",
    val emailError:String?=null,
    val password:String="",
    val passwordError:String?=null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val signUpSuccess: Boolean = false
)
