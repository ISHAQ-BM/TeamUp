package com.example.teamup.auth.data.source.remote

import com.example.teamup.auth.data.source.remote.model.ConfirmEmailRequest
import com.example.teamup.auth.data.source.remote.model.ExchangeResetCodeRequest
import com.example.teamup.auth.data.source.remote.model.ExchangeResetCodeResponse
import com.example.teamup.auth.data.source.remote.model.ForgotPasswordRequest
import com.example.teamup.auth.data.source.remote.model.LoginRequest
import com.example.teamup.auth.data.source.remote.model.RegisterRequest
import com.example.teamup.auth.data.source.remote.model.ResetPasswordRequest
import com.example.teamup.auth.data.source.remote.model.SignWithGoogleRequest
import com.example.teamup.auth.data.source.remote.model.TokenResponse
import com.example.teamup.auth.data.source.remote.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("v2/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<TokenResponse>





    @POST("v2/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<Unit>

    @POST("v2/auth/confirmEmail")
    suspend fun confirmEmail(
        @Body confirmEmailRequest: ConfirmEmailRequest
    ): Response<Unit>

    @POST("v2/auth/google")
    suspend fun signWithGoogle(
        @Body signWithGoogleRequest: SignWithGoogleRequest
    ): Response<Unit>

    @POST("v2/auth/forgotPassword")
    suspend fun forgotPassword(
        @Body forgotPasswordRequest: ForgotPasswordRequest
    ): Response<Unit>

    @POST("v2/auth/resendConfirmationEmail")
    suspend fun sendEmailVerification(
        @Body email:String
    ): Response<Unit>
    @POST("v3/auth/exchangeResetCodeForToken")
    suspend fun exchangeResetCodeForToken(
        @Body exchangeResetCodeRequest: ExchangeResetCodeRequest
    ):Response<ExchangeResetCodeResponse>

    @POST("v3/auth/resetPassword")
    suspend fun resetPassword(
        @Body resetPasswordRequest: ResetPasswordRequest
    ): Response<Unit>

    @GET("v2/auth/currenUser")
    suspend fun currentUser(): Response<User>


}
