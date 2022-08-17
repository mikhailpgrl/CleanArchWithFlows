package com.mikhailapps.architecture.domain.local.response

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.local.LocalCompletedResponse
import com.mikhailapps.architecture.domain.local.LocalCompletedResponse.LocalCompletedErrorResponse
import com.mikhailapps.architecture.domain.local.LocalCompletedResponse.LocalCompletedSuccessResponse
import com.mikhailapps.architecture.domain.local.LocalResponse
import com.mikhailapps.architecture.domain.local.LocalResponse.LocalErrorResponse
import com.mikhailapps.architecture.domain.local.LocalResponse.LocalSuccessResponse

fun <T> LocalResponse<T>.toResource(): Resource<T> {
    return when (this) {
        is LocalSuccessResponse -> Resource.success(data)
        is LocalErrorResponse -> Resource.failure(t, null)
    }
}

fun <T> LocalCompletedResponse.toResource(data: T): Resource<T> {
    return when (this) {
        is LocalCompletedSuccessResponse -> Resource.success(data)
        is LocalCompletedErrorResponse -> Resource.failure(t, data)
    }
}

fun LocalCompletedResponse.toResource(): Resource<Nothing> {
    return when (this) {
        is LocalCompletedSuccessResponse -> Resource.success()
        is LocalCompletedErrorResponse -> Resource.failure(t)
    }
}