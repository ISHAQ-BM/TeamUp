package com.example.teamup.auth.data.data_source.network_data_source.model

import com.squareup.moshi.Json

data class ResetPasswordRequest(
    @Json(name = "email")
    val email:String,
    @Json(name = "resetToken")
    val resetToken:String,
    @Json(name = "newPassword")
    val newPassword:String

)
