package com.example.teamup.auth.data.source.remote.model

data class TokenResponse(
    val accessToken: String,
    val expiresIn: Long,
    val refreshToken: String
)