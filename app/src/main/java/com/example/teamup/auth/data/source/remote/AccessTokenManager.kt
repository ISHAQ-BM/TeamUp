package com.example.teamup.auth.data.source.remote

import android.provider.Settings
import com.example.teamup.auth.data.source.local.AuthLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class AccessTokenManager @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val accessTokenApi: AccessTokenApi
) {

    fun getAccessToken(): String? {
            return runBlocking { authLocalDataSource.getAccessToken().first() }
    }

    fun getRefreshToken(): String {
            return runBlocking { authLocalDataSource.getRefreshToken().first() }
    }

    suspend fun refreshAccessToken(refreshToken:String): String? {
        val response=accessTokenApi.refreshAccessToken(refreshToken)
        if (response.isSuccessful){
            response.body()?.accessToken?.let { authLocalDataSource.updateAccessToken(it) }
            response.body()?.refreshToken?.let { authLocalDataSource.updateRefreshToken(it) }
            response.body()?.expiresIn?.let { authLocalDataSource.updateAccessTokenExpirationTime(it+System.currentTimeMillis()) }
            return response.body()?.accessToken
        }
        return null

    }


    fun isAccessTokenExpired(): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        val accessTokenExpirationTime= runBlocking { authLocalDataSource.getAccessTokenExpirationTime().first() }
        return accessTokenExpirationTime != null && currentTimeMillis >= accessTokenExpirationTime

    }
}
