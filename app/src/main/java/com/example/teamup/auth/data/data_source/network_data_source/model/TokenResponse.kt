package com.example.teamup.auth.data.data_source.network_data_source.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TokenResponse(
    val accessToken: String,
    val expiresIn: Long,
    val refreshToken: String
)