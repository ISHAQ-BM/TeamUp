package com.example.teamup.auth.data.source.remote

import com.example.teamup.auth.data.source.remote.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessTokenApi {

    @POST("v2/auth/refresh")
    suspend fun refreshAccessToken(
        @Body refreshTokenRequest: RefreshTokenRequest
    ): Response<TokenResponse>
}