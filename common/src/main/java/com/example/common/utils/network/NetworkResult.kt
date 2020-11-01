package com.example.common.utils.network

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : NetworkResult<T>()
    data class Error(val errorMessage: String) : NetworkResult<Nothing>()
}
