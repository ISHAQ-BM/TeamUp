package com.example.teamup.auth.data.data_source.network_data_source.model

import com.squareup.moshi.Json

data class RegisterRequest (
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
)