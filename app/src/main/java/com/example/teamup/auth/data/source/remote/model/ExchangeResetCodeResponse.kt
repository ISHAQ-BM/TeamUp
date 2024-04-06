package com.example.teamup.auth.data.source.remote.model

import com.squareup.moshi.Json

data class ExchangeResetCodeResponse (
    @Json(name = "resetToken")
    val resetToken: String
)