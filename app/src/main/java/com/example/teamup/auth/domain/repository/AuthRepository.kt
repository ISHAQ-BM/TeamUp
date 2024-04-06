package com.example.teamup.auth.domain.repository

import com.example.teamup.auth.data.source.remote.model.ExchangeResetCodeResponse
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String, ): Flow<Resource<Unit>>

    suspend fun signUpWithEmailAndPassword(fullName:String, email: String, password: String): Flow<Resource<Unit>>
    suspend fun signWithGoogle(googleIdToken:String ): Flow<Resource<Unit>>

    suspend fun sendEmailVerification(email:String): Flow<Resource<Unit>>

    suspend fun  sendPasswordResetEmail(email:String) : Flow<Resource<Unit>>

    suspend fun initiateGoogleOneTapFlow(): Flow<Resource<BeginSignInResult>>

    suspend fun exchangeResetCodeForToken(email: String,code: String):Flow<Resource<ExchangeResetCodeResponse>>

    suspend fun confirmEmail(email:String,code:String): Flow<Resource<Unit>>

    suspend fun resetPassword(email:String,resetToken:String,newPassword:String): Flow<Resource<Unit>>



}
