package com.example.teamup.core.repository

import com.example.teamup.core.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T> ): Resource<Any> {
        return withContext(Dispatchers.IO) {
            try {
                val response =  apiToBeCalled()
                if (response.isSuccessful) {
                    Resource.Success(data = response )
                } else {
                    Resource.Error(response.errorBody().toString() )
                }
            } catch (e: HttpException) {
                Resource.Error(message = e.message )
            } catch (e: IOException) {
                Resource.Error(message = e.message)
            } catch (e: Exception) {
                Resource.Error(message = e.message)
            }
        }
    }
}
