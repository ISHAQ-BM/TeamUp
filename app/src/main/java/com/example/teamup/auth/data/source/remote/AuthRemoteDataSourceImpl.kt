package com.example.teamup.auth.data.source.remote


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

class AuthRemoteDataSourceImpl @Inject
constructor(
    private val authApi: AuthNetwork,
    private val oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private val signUpRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private val signInRequest: BeginSignInRequest,
) : AuthRemoteDataSource, BaseRemoteDataSource() {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<Unit>> = safeApiCall { authApi.login(LoginRequest(email, password)) }

    override suspend fun signUserWithGoogle(googleIdToken: String): Flow<Resource<Unit>> =
        safeApiCall {
            authApi.signWithGoogle(
                SignWithGoogleRequest(googleIdToken)
            )
        }

    override suspend fun exchangeResetCodeForToken(
        email: String,
        code: String
    ): Flow<Resource<ExchangeResetCodeResponse>> = safeApiCall {
        authApi.exchangeResetCodeForToken(
            ExchangeResetCodeRequest(email, code)
        )
    }

    override suspend fun signUp(
        fullName: String,
        email: String,
        password: String
    ): Flow<Resource<Unit>> =
        safeApiCall { authApi.register(RegisterRequest(fullName, email, password)) }


    override suspend fun sendEmailVerification(email: String): Flow<Resource<Unit>> =
        safeApiCall {
            authApi.sendEmailVerification(email)
        }

    override suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Unit>> =
        safeApiCall {
            authApi.forgotPassword(ForgotPasswordRequest(email))
        }

    override suspend fun initiateGoogleOneTapFlow(): Flow<Resource<BeginSignInResult>> =
        performGoogleSignUser {
            try {
                oneTapClient.beginSignIn(signInRequest).await()
            } catch (e: Exception) {
                oneTapClient.beginSignIn(signUpRequest).await()
            }
        }

    override suspend fun confirmEmail(email: String, code: String): Flow<Resource<Unit>> {
        return safeApiCall {
            authApi.confirmEmail(ConfirmEmailRequest(email, code))
        }
    }

    override suspend fun resetPassword(
        email: String,
        resetCode: String,
        newPassword: String
    ): Flow<Resource<Unit>> =
        safeApiCall {
            authApi.resetPassword(ResetPasswordRequest(email, resetCode, newPassword))
        }
}