package dev.ferrant.quickcoin.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Encapsulates the Retrofit Response in a ResponseResult to improve error management
 */
inline fun <reified T> Response<T>.getResponse(): Flow<ResponseResult<T>> = flow {
    try {
        val responseBody = body()
        if (isSuccessful && responseBody != null) {
            emit(ResponseResult.success(responseBody))

        } else {
            emit(ResponseResult.error(message()))
        }
    } catch (e: Exception) {
        emit(ResponseResult.error(e.message ?: e.toString()))
    }
}
