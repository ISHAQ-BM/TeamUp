package com.example.teamup.auth.domain.use_case

import com.example.teamup.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpWithEmailAndPasswordUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        suspend operator fun invoke(
            fullName:String,
            email: String,
            password: String,
        ) = authRepository.signUpWithEmailAndPassword(fullName,email, password)
    }
