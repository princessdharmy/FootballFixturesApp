package com.example.data.utils

import com.example.common.utils.Result
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        return Result.Error(HttpException(response))
    } catch (e: Exception) {
        return Result.Error(e)
    }
}