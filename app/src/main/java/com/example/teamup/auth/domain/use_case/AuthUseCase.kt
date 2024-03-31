package com.example.teamup.auth.domain.use_case

data class AuthUseCase(
    val logInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    val signUpUseCase: SignUpUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val confirmEmailUseCase: ConfirmEmailUseCase,
    val signUserWithOneTapUseCase: `InitiateGoogleOneTapFlow()`,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val validateDisplayNameUseCase: ValidateDisplayNameUseCase
)
