package com.example.teamup.auth.data.data_source.network_data_source.api_service

import com.example.teamup.auth.data.data_source.network_data_source.model.ConfirmEmailRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.LoginRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.RegisterRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.ResetPasswordRequest
import com.example.teamup.auth.data.data_source.network_data_source.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<Unit>

    @POST("auth/refresh")
    suspend fun refreshAccessToken(
        @Body refreshToken:String,
    ): TokenResponse




    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<Unit>

    @POST("auth/confirmEmail")
    suspend fun confirmEmail(
        @Body confirmEmailRequest: ConfirmEmailRequest
    ): Response<Unit>

    @POST("auth/forgotPassword")
    suspend fun forgotPassword(
        @Body email:String
    ): Response<Unit>

    @POST("auth/resendConfirmationEmail")
    suspend fun sendEmailVerification(
        @Body email:String
    ): Response<Unit>

    @POST("auth/resetPassword")
    suspend fun resetPassword(
        @Body resetPasswordRequest: ResetPasswordRequest
    ): Response<Unit>


}
