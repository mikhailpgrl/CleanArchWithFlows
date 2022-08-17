package com.mikhailapps.architecture.domain.remote.response

import com.mikhailapps.architecture.domain.Resource

fun <T, U> RemoteResponse<T, U>.toResource(): Resource<T> {
    return when (this) {
        is RemoteResponse.Success -> Resource.success(body)
        is RemoteResponse.ApiError -> Resource.failure(
            RemoteResponseThrowable.ApiErrorThrowable(
                "Api error",
                this.code
            )
        )
        is RemoteResponse.NetworkError -> Resource.failure(RemoteResponseThrowable.NetworkErrorThrowable)
        is RemoteResponse.UnknownError -> Resource.failure(RemoteResponseThrowable.UnknownErrorThrowable)
    }
}