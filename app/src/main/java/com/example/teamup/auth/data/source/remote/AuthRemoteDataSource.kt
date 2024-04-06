package com.example.teamup.auth.data.source.remote

import android.util.Log
import com.example.teamup.auth.core.SIGN_IN_REQUEST
import com.example.teamup.auth.core.SIGN_UP_REQUEST
import com.example.teamup.auth.data.source.remote.model.ConfirmEmailRequest
import com.example.teamup.auth.data.source.remote.model.ExchangeResetCodeRequest
import com.example.teamup.auth.data.source.remote.model.ExchangeResetCodeResponse
import com.example.teamup.auth.data.source.remote.model.ForgotPasswordRequest
import com.example.teamup.auth.data.source.remote.model.LoginRequest
import com.example.teamup.auth.data.source.remote.model.RegisterRequest
import com.example.teamup.auth.data.source.remote.model.ResetPasswordRequest
import com.example.teamup.auth.data.source.remote.model.SignWithGoogleRequest
import com.example.teamup.core.BaseRemoteDataSource
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

interface AuthRemoteDataSource {
    suspend fun signInWithEmailAndPassword(email: String, password: String, ): Flow<Resource<Unit>>
    suspend fun signUserWithGoogle(googleIdToken:String):Flow<Resource<Unit>>
    suspend fun exchangeResetCodeForToken(email:String, code: String):Flow<Resource<ExchangeResetCodeResponse>>

    suspend fun signUp(fullName:String, email: String, password: String, ): Flow<Resource<Unit>>

    suspend fun sendEmailVerification(email:String): Flow<Resource<Unit>>
    suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Unit>>

    suspend fun initiateGoogleOneTapFlow() :Flow<Resource<BeginSignInResult>>
    suspend fun confirmEmail(email:String,code:String): Flow<Resource<Unit>>

    suspend fun resetPassword(email:String,resetCode:String,newPassword:String): Flow<Resource<Unit>>
}


