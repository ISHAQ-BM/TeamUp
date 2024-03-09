package com.example.teamup.auth.domain.use_case

import com.example.teamup.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        suspend operator fun invoke(
            email: String,
            password: String,
        ) = authRepository.register(email, password)
    }
