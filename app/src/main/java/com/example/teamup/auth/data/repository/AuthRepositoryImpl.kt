package com.example.teamup.auth.data.repository

import com.example.teamup.auth.data.data_source.network_data_source.AuthRemoteDataSource
import com.example.teamup.auth.domain.repository.AuthRepository
import com.example.teamup.core.model.Resource
import com.example.teamup.core.repository.BaseRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val authRemoteDataSource: AuthRemoteDataSource,
    ) : BaseRepository(), AuthRepository {
        override suspend fun signInWithEmailAndPassword(
            email: String,
            password: String,
        ): Resource<Any> {
            return safeApiCall { authRemoteDataSource.signInWithEmailAndPassword(email, password) }

        }

        override suspend fun register(
            email: String,
            password: String,
        ): Resource<Any> {
            return safeApiCall { authRemoteDataSource.register(email, password) }

        }

        override suspend fun sendEmailVerification(): Resource<Any> {
            return safeApiCall { authRemoteDataSource.sendEmailVerification() }

        }
    }
