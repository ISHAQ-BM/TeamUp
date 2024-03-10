package com.example.teamup.core.repository

import com.example.teamup.core.model.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

        return try {
            val response = apiToBeCalled()
            if (response.isSuccessful) {
                Resource.Success(data = response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (e: HttpException) {
            Resource.Error(message = e.message)
        } catch (e: IOException) {
            Resource.Error(message = e.message)
        } catch (e: Exception) {
            Resource.Error(message = e.message)
        }
    }


    suspend fun <T> performGoogleSignUser(signUser: suspend () -> T): Resource<T> {
        return try {
            val signUserResult = signUser()
            Resource.Success(signUserResult)
        } catch (e: HttpException) {
            Resource.Error(message = e.message)
        } catch (e: IOException) {
            Resource.Error(message = e.message)
        } catch (e: Exception) {
            Resource.Error(message = e.message)
        }
    }
}




