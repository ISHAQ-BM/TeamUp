package com.example.teamup.core

import com.example.teamup.core.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRemoteDataSource {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Flow<Resource<T>> = flow{
        emit(Resource.Loading())
        try {
            val response = apiToBeCalled()
            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()!!))
            } else {
                emit(Resource.Error(response.errorBody().toString()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }


    suspend fun <T> performGoogleSignUser(signUser: suspend () -> T): Flow<Resource<T>> = flow {
        emit(Resource.Loading())
        try {
            val signUserResult = signUser()
            emit(Resource.Success(signUserResult))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}




