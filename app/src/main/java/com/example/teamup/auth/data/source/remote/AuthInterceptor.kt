package com.example.teamup.auth.data.source.remote

import com.example.teamup.auth.data.source.local.AuthLocalDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val accessTokenManager: AccessTokenManager
):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = accessTokenManager.getAccessToken()


        if (accessToken != null && accessTokenManager.isAccessTokenExpired()) {
            val refreshToken = accessTokenManager.getRefreshToken()
            val refreshedAccessToken = runBlocking {
                accessTokenManager.refreshAccessToken(refreshToken)
            }

            if (refreshedAccessToken != null) {
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $refreshedAccessToken")
                    .build()
                return chain.proceed(newRequest)
            }
        }

        val authorizedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(authorizedRequest)


    }

}