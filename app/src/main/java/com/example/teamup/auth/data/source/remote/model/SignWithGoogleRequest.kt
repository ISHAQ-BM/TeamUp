package com.example.teamup.auth.data.source.remote.model

import com.squareup.moshi.Json

data class SignWithGoogleRequest (
    @Json(name = "idToken")
    val idToken:String,
)