package com.example.data.utils

import com.example.common.utils.CONNECT_EXCEPTION
import com.example.common.utils.SOCKET_TIME_OUT_EXCEPTION
import com.example.common.utils.UNKNOWN_HOST_EXCEPTION
import com.example.common.utils.UNKNOWN_NETWORK_EXCEPTION
import com.example.common.utils.network.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/*suspend fun <T : Any> safeApiCall(call: suspend () -> Response<NetworkResponse<T>>): NetworkStatus<T> {
    try {
        val response = call.invoke()
        return when {
            response.isSuccessful -> {
                Log.e("SafeApiCall", "response: $response, body: ${response.body()}, data: ${response.body()?.data}")
                NetworkStatus.Success(response.body()?.data, response.body())
            }
            response.isSuccessful.not() -> {
                throw HttpException(response)
            }
            else -> {
                NetworkStatus.Error(
                    response.body()?.errorMessage,
                    networkResponse = response.body()
                )
            }
        }
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> {
                NetworkStatus.Error(CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                NetworkStatus.Error(UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                NetworkStatus.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}*/

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            if (response.body() != null) {
                return NetworkResult.Success(response.body())
            }
        }
        return NetworkResult.Error(response.message())
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> {
                NetworkResult.Error(CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                NetworkResult.Error(UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                NetworkResult.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                NetworkResult.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                NetworkResult.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}