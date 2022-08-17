package com.mikhailapps.architecture.domain.remote.response

import java.io.IOException

sealed class RemoteResponse<out T, out U> {
    /**
     * Success response with body
     */
    data class Success<T>(val body: T) : RemoteResponse<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U>(val body: U, val code: Int) : RemoteResponse<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : RemoteResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?) : RemoteResponse<Nothing, Nothing>()
}
