package com.example.teamup.auth.data.data_source.network_data_source.model

data class ResetPasswordRequest(
    val email:String,
    val resetCode:String,
    val newPassword:String
)
