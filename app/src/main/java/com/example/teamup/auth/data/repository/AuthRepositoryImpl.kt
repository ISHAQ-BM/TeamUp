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

    override suspend fun register(
        email: String,
        password: String,
    ): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.register(email, password) }

    }

    override suspend fun sendEmailVerification(): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.sendEmailVerification() }

    }

    override suspend fun sendPasswordResetEmail(email: String): Resource<Unit> {
        return safeApiCall { authRemoteDataSource.sendPasswordResetEmail(email) }
    }

    override suspend fun signUserWithOneTap(): Resource<BeginSignInResult> {
        return performGoogleSignUser { authRemoteDataSource.signUserWithOneTap() }
    }

}
