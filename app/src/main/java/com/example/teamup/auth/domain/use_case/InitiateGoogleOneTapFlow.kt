package com.example.teamup.auth.domain.use_case

import com.example.teamup.auth.domain.repository.AuthRepository
import javax.inject.Inject

class InitiateGoogleOneTapFlow @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke()=authRepository.initiateGoogleOneTapFlow()
}