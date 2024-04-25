package com.example.teamup.auth.data.source.remote

import com.squareup.moshi.Json

data class RefreshTokenRequest(
    @Json(name = "refreshToken")
    val refreshToken: String,
)