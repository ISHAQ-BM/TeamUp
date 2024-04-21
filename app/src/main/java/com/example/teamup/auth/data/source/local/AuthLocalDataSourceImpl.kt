package com.example.teamup.auth.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthLocalDataSource {

    private companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val ACCESS_TOKEN_EXPIRATION_TIME = stringPreferencesKey("access_token_expiration_time")
    }

    override fun getAccessToken(): Flow<String?> =
        dataStore.data.catch {
            emit(emptyPreferences())
        }
            .map { preferences ->
                preferences[ACCESS_TOKEN]
            }

    override suspend fun updateAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }


    override fun getRefreshToken(): Flow<String> =
        dataStore.data.catch {
            emit(emptyPreferences())
        }
            .map { preferences ->
                preferences[REFRESH_TOKEN] ?: ""
            }

    override suspend fun updateRefreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    override fun getAccessTokenExpirationTime(): Flow<Long?> =
        dataStore.data.catch {
            emit(emptyPreferences())
        }
            .map { preferences ->
                preferences[ACCESS_TOKEN_EXPIRATION_TIME]?.toLong()
            }

    override suspend fun updateAccessTokenExpirationTime(expireIn: Long) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_EXPIRATION_TIME] = expireIn.toString()
        }
    }
}