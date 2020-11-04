package com.example.data.utils

import com.example.common.utils.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline clearData: suspend () -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline shouldClear: (RequestType, ResultType) -> Boolean = { requestType: RequestType, resultType: ResultType -> false }
) = flow<NetworkStatus<ResultType>> {

    emit(NetworkStatus.Loading(null))

    val dbData = query().first()
    val flow = if (shouldFetch(dbData)) {
        emit(NetworkStatus.Loading(dbData))
        try {
            val fetchData = fetch()
            if (shouldClear(fetchData, dbData)) {
                clearData()
            }
            saveFetchResult(fetchData)
            query().map { NetworkStatus.Success(it) }

        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { NetworkStatus.Error(throwable.message, it) }
        }

    } else {
        query().map { NetworkStatus.Success(it) }
    }

    emitAll(flow)
}