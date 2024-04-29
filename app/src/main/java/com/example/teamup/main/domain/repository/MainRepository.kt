package com.example.teamup.main.domain.repository

import com.example.teamup.auth.data.source.remote.model.User
import com.example.teamup.core.model.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    val userData: Flow<User>
    suspend fun getCurrentUser(): Flow<Resource<User>>
}