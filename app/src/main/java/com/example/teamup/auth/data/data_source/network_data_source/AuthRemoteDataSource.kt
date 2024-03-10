package com.example.teamup.auth.data.data_source.network_data_source

import com.example.teamup.auth.data.data_source.network_data_source.api_service.AuthApi
import com.example.teamup.auth.data.data_source.network_data_source.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource
    @Inject
    constructor(
        private val authApi: AuthApi,
    ) {
        suspend fun signInWithEmailAndPassword(
            email: String,
            password: String,
        ): Response<Unit> =
            withContext(Dispatchers.IO) {
                authApi.login(LoginRequest(email, password))
            }

        suspend fun register(
            email: String,
            password: String,
        ): Response<Unit> =
            withContext(Dispatchers.IO) {
                authApi.register(LoginRequest(email, password))
            }

        suspend fun sendEmailVerification(): Response<Unit> =
            withContext(Dispatchers.IO) {
                authApi.confirmEmail()
            }

    suspend fun sendPasswordResetEmail(email: String) : Response<Unit> =
        withContext(Dispatchers.IO){
            authApi.forgotPassword(email)
        }
    }
