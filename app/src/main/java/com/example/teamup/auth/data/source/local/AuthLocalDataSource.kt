package com.example.teamup.auth.data.source.local

import com.example.teamup.auth.data.source.remote.model.User
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    val userData: Flow<User>

    suspend fun updateEmailConfirmationStatus()

    suspend fun setUserData(user: User)

    fun getAccessToken(): Flow<String?>

    suspend fun updateAccessToken(accessToken: String)

    fun getRefreshToken(): Flow<String>

    suspend fun updateRefreshToken(refreshToken: String)

    fun getAccessTokenExpirationTime(): Flow<Long?>

    suspend fun updateAccessTokenExpirationTime(expireIn: Long)


}