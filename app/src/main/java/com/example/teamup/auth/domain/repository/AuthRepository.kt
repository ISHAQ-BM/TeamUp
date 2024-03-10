package com.example.teamup.auth.domain.repository

import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Resource<Unit>

    suspend fun register(
        email: String,
        password: String,
    ): Resource<Unit>

    suspend fun sendEmailVerification(): Resource<Unit>

    suspend fun  sendPasswordResetEmail(email:String) : Resource<Unit>

    suspend fun signUserWithOneTap(): Resource<BeginSignInResult>



}
