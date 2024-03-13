package com.example.teamup.auth.domain.use_case

import com.example.teamup.auth.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    val authRepository: AuthRepository
) {
   suspend operator fun invoke(email:String
                               ,resetCode:String,
                               newPassword:String
   )=authRepository.resetPassword(email, resetCode, newPassword)
}