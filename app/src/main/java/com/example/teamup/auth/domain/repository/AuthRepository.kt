package com.example.teamup.auth.domain.repository

import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Resource<Unit>

    suspend fun signUp(
        email: String,
        password: String,
    ): Resource<Unit>

    suspend fun sendEmailVerification(email:String): Resource<Unit>

    suspend fun  sendPasswordResetEmail(email:String) : Resource<Unit>

    suspend fun signUserWithOneTap(): Resource<BeginSignInResult>

    suspend fun confirmEmail(email:String,code:String):Resource<Unit>

    suspend fun resetPassword(email:String,resetCode:String,newPassword:String): Resource<Unit>



}
