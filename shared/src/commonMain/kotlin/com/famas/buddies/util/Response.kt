package com.famas.buddies.util

sealed class Response<T>(val data: T?, val message: String?) {
    class Success<T>(data: T? = null, message: String? = null) :
        Response<T>(data = data, message = message)
    class Loading<T> : Response<T>(data = null, message = null)
    class Failure<T>(message: String, data: T? = null) : Response<T>(data = data, message = message)
}