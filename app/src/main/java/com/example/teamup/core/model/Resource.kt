package com.example.teamup.core.model


sealed class Resource<T>{
    class Success<T>(data: T) : Resource<T>()

    class Error<T>(message: String?, data: T? = null) : Resource<T>()

    class Loading<T> : Resource<T>()
}
