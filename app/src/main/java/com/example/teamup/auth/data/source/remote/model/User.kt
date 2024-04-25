package com.example.teamup.auth.data.source.remote.model


data class User(
    val email: String,
    val id: String,
    val isEmailConfirmed: Boolean,
    val userName: String
)