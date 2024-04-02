package com.example.teamup.auth.data.data_source.network_data_source.api_service

import com.example.teamup.auth.data.data_source.network_data_source.model.ConfirmEmailRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.ExchangeResetCodeRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.LoginRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.RegisterRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.ResetPasswordRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("v2/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<Unit>

    @POST("v2/auth/refresh")
    suspend fun refreshAccessToken(
        @Body refreshToken:String,
    ): TokenResponse




    @POST("v2/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<Unit>

    @POST("v2/auth/confirmEmail")
    suspend fun confirmEmail(
        @Body confirmEmailRequest: ConfirmEmailRequest
    ): Response<Unit>

    @POST("v2/auth/google")
    suspend fun signWithGoogle(
        @Body idToken: String
    ): Response<Unit>

    @POST("v2/auth/forgotPassword")
    suspend fun forgotPassword(
        @Body email:String
    ): Response<Unit>

    @POST("v2/auth/resendConfirmationEmail")
    suspend fun sendEmailVerification(
        @Body email:String
    ): Response<Unit>
    @POST("v3/auth/exchangeResetCodeForToken")
    suspend fun exchangeResetCodeForToken(
        @Body exchangeResetCodeRequest: ExchangeResetCodeRequest
    ):Response<String>

    @POST("v3/auth/resetPassword")
    suspend fun resetPassword(
        @Body resetPasswordRequest: ResetPasswordRequest
    ): Response<Unit>


}
