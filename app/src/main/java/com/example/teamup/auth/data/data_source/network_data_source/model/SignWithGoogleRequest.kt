package com.example.teamup.auth.data.data_source.network_data_source.model

import com.squareup.moshi.Json

data class SignWithGoogleRequest (
    @Json(name = "idToken")
    val idToken:String,
)