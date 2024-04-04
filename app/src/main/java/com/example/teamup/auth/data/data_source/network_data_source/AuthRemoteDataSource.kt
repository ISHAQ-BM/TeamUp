package com.example.teamup.auth.data.data_source.network_data_source

import android.util.Log
import com.example.teamup.auth.core.SIGN_IN_REQUEST
import com.example.teamup.auth.core.SIGN_UP_REQUEST
import com.example.teamup.auth.data.data_source.network_data_source.api_service.AuthApi
import com.example.teamup.auth.data.data_source.network_data_source.model.ConfirmEmailRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.ExchangeResetCodeRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.ExchangeResetCodeResponse
import com.example.teamup.auth.data.data_source.network_data_source.model.ForgotPasswordRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.LoginRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.RegisterRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.ResetPasswordRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.SignWithGoogleRequest
import com.example.teamup.core.BaseRemoteDataSource
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
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
    private val signInRequest: BeginSignInRequest,

) :BaseRemoteDataSource() {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<Unit>> = safeApiCall { authApi.login(LoginRequest(email, password)) }

    suspend fun signUserWithGoogle(
        googleIdToken:String

    ):Flow<Resource<Unit>> = safeApiCall { authApi.signWithGoogle(SignWithGoogleRequest(googleIdToken)) }

    suspend fun exchangeResetCodeForToken(
        email:String,
        code: String
    ):Flow<Resource<ExchangeResetCodeResponse>> = safeApiCall { authApi.exchangeResetCodeForToken(
        ExchangeResetCodeRequest(email, code)
    ) }





    suspend fun signUp(
        fullName:String,
        email: String,
        password: String,
    ): Flow<Resource<Unit>> = safeApiCall { authApi.register(RegisterRequest(fullName,email, password))}


    suspend fun sendEmailVerification(email:String): Flow<Resource<Unit>> =
        safeApiCall{
            authApi.sendEmailVerification(email)
        }

    suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Unit>> =
        safeApiCall {
            authApi.forgotPassword(ForgotPasswordRequest(email))
        }

    suspend fun initiateGoogleOneTapFlow() :Flow<Resource<BeginSignInResult>> =
        performGoogleSignUser{
            try {
                oneTapClient.beginSignIn(signInRequest).await()
            } catch (e: Exception) {
                oneTapClient.beginSignIn(signUpRequest).await()
            }
        }

    suspend fun confirmEmail(email:String,code:String): Flow<Resource<Unit>> {
        Log.d("email",email)
        Log.d("code",code)
        return   safeApiCall {
            authApi.confirmEmail(ConfirmEmailRequest(email,code))
        }
    }


    suspend fun resetPassword(email:String,resetCode:String,newPassword:String): Flow<Resource<Unit>> =
        safeApiCall {
            authApi.resetPassword(ResetPasswordRequest(email,resetCode, newPassword))
        }


}


