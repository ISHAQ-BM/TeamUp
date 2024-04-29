package com.example.teamup.auth.data.source.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val email: String? = null,
    val id: String? = null,
    val isEmailConfirmed: Boolean = false,
    val userName: String? = null
)