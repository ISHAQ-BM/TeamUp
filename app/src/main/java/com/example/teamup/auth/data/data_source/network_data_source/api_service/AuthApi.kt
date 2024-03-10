package com.example.teamup.auth.data.data_source.network_data_source.api_service

import com.example.teamup.auth.data.data_source.network_data_source.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("v1/auth/login?useCookies=true")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<Unit>

    @POST("v1/auth/register")
    suspend fun register(
        @Body loginRequest: LoginRequest,
    ): Response<Unit>

    @GET("v1/auth/confirmEmail")
    suspend fun confirmEmail(): Response<Unit>

    @POST("v1/auth/forgotPassword")
    suspend fun forgotPassword(
        @Body email:String
    ): Response<Unit>
}
