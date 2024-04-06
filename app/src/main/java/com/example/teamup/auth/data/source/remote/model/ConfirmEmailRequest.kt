package com.example.teamup.auth.data.source.remote.model

import com.squareup.moshi.Json

data class ConfirmEmailRequest (
    @Json(name = "email")
    val email:String,
    @Json(name = "code")
    val code:String
)