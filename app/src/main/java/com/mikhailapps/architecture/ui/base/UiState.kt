package com.mikhailapps.architecture.ui.base

import com.mikhailapps.architecture.domain.Resource

sealed class UiState<T> {
    data class Idle<T>(val data: T? = null) : UiState<T>()

    data class Progress<T>(val progression: Int?, val data: T?) : UiState<T>()

    data class Success<T>(val data: T?) : UiState<T>()

    data class Failure<T>(val message: String, val data: T? = null) : UiState<T>()

    companion object {

        fun <T> loading(progression: Int? = null, data: T? = null): UiState<T> =
            Progress(progression, data)

        fun <T> idle(data: T? = null): UiState<T> = Idle(data)

        fun <T> success(data: T? = null): UiState<T> = Success(data)

        fun <T> failure(message: String, data: T? = null): UiState<T> =
            Failure(message, data)
    }
}

fun <T> Resource<T>.asUiState(): UiState<T> {
    return when (this) {
        is Resource.Idle -> UiState.idle(data)
        is Resource.Progress -> UiState.loading(progression, data)
        is Resource.Success -> UiState.success(data)
        is Resource.Failure -> UiState.failure(throwable.message.toString(), data)
    }
}

fun <T, S> Resource<T>.asUiState(mappedData: (T?) -> S?): UiState<S> {
    return when (this) {
        is Resource.Idle -> UiState.idle(mappedData(data))
        is Resource.Progress -> UiState.loading(progression, mappedData(data))
        is Resource.Success -> UiState.success(mappedData(data))
        is Resource.Failure -> UiState.failure(throwable.message.toString(), mappedData(data))
    }
}