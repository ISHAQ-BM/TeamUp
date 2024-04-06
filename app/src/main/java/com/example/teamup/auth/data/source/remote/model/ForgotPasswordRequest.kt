package com.example.teamup.auth.data.source.remote.model

import com.squareup.moshi.Json

data class ForgotPasswordRequest (
    @Json(name = "email")
    val email: String,
)