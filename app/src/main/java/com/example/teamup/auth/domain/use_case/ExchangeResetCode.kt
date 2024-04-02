package com.example.teamup.auth.domain.use_case

import com.example.teamup.auth.domain.repository.AuthRepository
import javax.inject.Inject

class ExchangeResetCode @Inject constructor(
    val authRepository: AuthRepository
) {
    suspend operator fun invoke(email:String,code:String)=authRepository.exchangeResetCodeForToken(email, code)

}