package com.example.teamup.auth.domain.use_case

data class AuthUseCase(
    val logInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    val registerWithEmailAndPasswordUseCase: RegisterUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
