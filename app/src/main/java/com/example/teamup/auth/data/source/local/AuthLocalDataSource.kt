package com.example.teamup.auth.data.source.local

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    fun getAccessToken(): Flow<String?>

    suspend fun updateAccessToken(accessToken: String)

    fun getRefreshToken(): Flow<String>

    suspend fun updateRefreshToken(refreshToken: String)

    fun getAccessTokenExpirationTime(): Flow<Long?>

    suspend fun updateAccessTokenExpirationTime(expireIn: Long)


}