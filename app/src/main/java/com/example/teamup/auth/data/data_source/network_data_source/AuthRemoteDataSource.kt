package com.example.teamup.auth.data.data_source.network_data_source

import com.example.teamup.auth.core.SIGN_IN_REQUEST
import com.example.teamup.auth.core.SIGN_UP_REQUEST
import com.example.teamup.auth.data.data_source.network_data_source.api_service.AuthApi
import com.example.teamup.auth.data.data_source.network_data_source.model.LoginRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class AuthRemoteDataSource
@Inject
constructor(
    private val authApi: AuthApi,
    private val oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private val signUpRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private val signInRequest: BeginSignInRequest
) {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Response<Unit> =
        withContext(Dispatchers.IO) {
            authApi.login(LoginRequest(email, password))
        }

    suspend fun register(
        email: String,
        password: String,
    ): Response<Unit> =
        withContext(Dispatchers.IO) {
            authApi.register(LoginRequest(email, password))
        }

    suspend fun sendEmailVerification(): Response<Unit> =
        withContext(Dispatchers.IO) {
            authApi.confirmEmail()
        }

    suspend fun sendPasswordResetEmail(email: String): Response<Unit> =
        withContext(Dispatchers.IO) {
            authApi.forgotPassword(email)
        }

    suspend fun signUserWithOneTap(): BeginSignInResult =
        withContext(Dispatchers.IO) {
            try {
                oneTapClient.beginSignIn(signInRequest).await()
            } catch (e: Exception) {
                oneTapClient.beginSignIn(signUpRequest).await()
            }
        }


}


