package com.example.teamup.auth.data.repository

import com.example.teamup.auth.core.SIGN_IN_REQUEST
import com.example.teamup.auth.core.SIGN_UP_REQUEST
import com.example.teamup.auth.data.data_source.network_data_source.AuthRemoteDataSource
import com.example.teamup.auth.domain.repository.AuthRepository
import com.example.teamup.core.model.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.flow.Flow
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
) :  AuthRepository {
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<Unit>> =
         authRemoteDataSource.signInWithEmailAndPassword(email, password)



    override suspend fun signUpWithEmailAndPassword(
        fullName:String,
        email: String,
        password: String,
    ): Flow<Resource<Unit>>  = authRemoteDataSource.signUp(fullName,email, password)

    override suspend fun signWithGoogle(
        googleIdToken: String
    ): Flow<Resource<Unit>> = authRemoteDataSource.signUserWithGoogle(googleIdToken)


    override suspend fun sendEmailVerification(email:String):Flow<Resource<Unit>> = authRemoteDataSource.sendEmailVerification(email)



    override suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Unit>> = authRemoteDataSource.sendPasswordResetEmail(email)

    override suspend fun initiateGoogleOneTapFlow(): Flow<Resource<BeginSignInResult>> =
        authRemoteDataSource.initiateGoogleOneTapFlow()

    override suspend fun exchangeResetCodeForToken(
        email: String,
        code: String
    ): Flow<Resource<String>> =
        authRemoteDataSource.exchangeResetCodeForToken(email, code)



    override suspend fun confirmEmail(email: String, code: String): Flow<Resource<Unit>> =
         authRemoteDataSource.confirmEmail(email,code)

    override suspend fun resetPassword(
        email: String,
        resetToken: String,
        newPassword: String
    ): Flow<Resource<Unit>> =
         authRemoteDataSource.resetPassword(email, resetToken, newPassword)

}
