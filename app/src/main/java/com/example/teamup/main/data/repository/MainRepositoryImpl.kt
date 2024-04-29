package com.example.teamup.main.data.repository

import com.example.teamup.auth.data.source.local.AuthLocalDataSource
import com.example.teamup.auth.data.source.remote.AuthRemoteDataSource
import com.example.teamup.auth.data.source.remote.model.User
import com.example.teamup.core.model.Resource
import com.example.teamup.main.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : MainRepository {

    override val userData: Flow<User>
        get() = authLocalDataSource.userData
    override suspend fun getCurrentUser(): Flow<Resource<User>> =
        authRemoteDataSource.getCurrentUser()
}