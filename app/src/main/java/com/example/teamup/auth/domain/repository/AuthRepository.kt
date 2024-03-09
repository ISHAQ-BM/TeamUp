package com.example.teamup.auth.domain.repository

import com.example.teamup.core.model.Resource

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Resource<Any>

    suspend fun register(
        email: String,
        password: String,
    ): Resource<Any>

    suspend fun sendEmailVerification(): Resource<Any>
}
