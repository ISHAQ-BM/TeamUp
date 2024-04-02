package com.example.teamup.auth.domain.use_case

data class AuthUseCase(
    val logInWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase,
    val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateFullNameUseCase: ValidateFullNameUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val confirmEmailUseCase: ConfirmEmailUseCase,
    val initiateGoogleOneTapFlowUseCase: InitiateGoogleOneTapFlow,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val signUserWithGoogleUseCse: SignUserWithGoogleUseCse,
    val sendVerificationEmailUseCase: SendVerificationEmailUseCase,
    val exchangeResetCode: ExchangeResetCode
)
