package com.example.teamup.auth.data.repository

import com.example.teamup.auth.core.SIGN_IN_REQUEST
import com.example.teamup.auth.core.SIGN_UP_REQUEST
import com.example.teamup.auth.data.data_source.network_data_source.AuthRemoteDataSource
import com.example.teamup.auth.domain.repository.AuthRepository
import com.example.teamup.core.model.Resource
import com.example.teamup.core.repository.BaseRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl
@Inject
constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private val signUpRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private val signInRequest: BeginSignInRequest,
) : BaseRepository(), AuthRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.signInWithEmailAndPassword(email, password) }

    }

    override suspend fun signUp(
        email: String,
        password: String,
    ): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.signUp(email, password) }

    }

    override suspend fun sendEmailVerification(email:String): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.sendEmailVerification(email) }

    }

    override suspend fun sendPasswordResetEmail(email: String): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.sendPasswordResetEmail(email) }
    }

    override suspend fun signUserWithOneTap(): Resource<BeginSignInResult> {
        return performGoogleSignUser { authRemoteDataSource.signUserWithOneTap() }
    }

    override suspend fun confirmEmail(email: String, code: String): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.confirmEmail(email,code) }
    }

    override suspend fun resetPassword(
        email: String,
        resetCode: String,
        newPassword: String
    ): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.resetPassword(email, resetCode, newPassword) }
    }

}
