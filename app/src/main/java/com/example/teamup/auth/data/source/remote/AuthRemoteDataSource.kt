package com.example.teamup.auth.data.source.remote

import com.example.teamup.auth.data.source.remote.model.ExchangeResetCodeResponse
import com.example.teamup.auth.data.source.remote.model.TokenResponse
import com.example.teamup.auth.data.source.remote.model.User
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    suspend fun getCurrentUser(): Flow<Resource<User>>
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<TokenResponse>>
    suspend fun signUserWithGoogle(googleIdToken:String):Flow<Resource<Unit>>
    suspend fun exchangeResetCodeForToken(email:String, code: String):Flow<Resource<ExchangeResetCodeResponse>>

    suspend fun signUp(fullName:String, email: String, password: String, ): Flow<Resource<Unit>>

    suspend fun sendEmailVerification(email:String): Flow<Resource<Unit>>
    suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Unit>>

    suspend fun initiateGoogleOneTapFlow() :Flow<Resource<BeginSignInResult>>
    suspend fun confirmEmail(email:String,code:String): Flow<Resource<Unit>>

    suspend fun resetPassword(email:String,resetCode:String,newPassword:String): Flow<Resource<Unit>>


}


