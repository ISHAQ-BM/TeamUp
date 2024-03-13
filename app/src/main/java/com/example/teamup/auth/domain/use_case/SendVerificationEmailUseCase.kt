package com.example.teamup.auth.domain.use_case

import com.example.teamup.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SendVerificationEmailUseCase @Inject constructor(
    val authRepository: AuthRepository
) {
    suspend operator fun invoke(email:String)=authRepository.sendEmailVerification(email)
}