package com.example.common.utils.network

data class NetworkResponse<T> (
    var code: Int = 0,
    var data: T?,
    var errorMessage: String? = ""
)