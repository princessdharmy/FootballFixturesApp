package com.example.common.utils.network

sealed class NetworkStatus<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : NetworkStatus<T>(data)
    class Error<T>(errorMessage: String?, data: T? = null) : NetworkStatus<T>(data, errorMessage)
    class Loading<T>(data: T? = null) : NetworkStatus<T>(data)
}