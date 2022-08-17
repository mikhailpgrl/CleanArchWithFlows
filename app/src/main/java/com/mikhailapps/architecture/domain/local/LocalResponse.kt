package com.mikhailapps.architecture.domain.local

sealed class LocalResponse<T> {
    class LocalSuccessResponse<T>(val data: T?) : LocalResponse<T>()
    class LocalErrorResponse<T>(val t: Throwable, val data: T? = null) : LocalResponse<T>()
}

sealed class LocalCompletedResponse {
    object LocalCompletedSuccessResponse : LocalCompletedResponse()
    class LocalCompletedErrorResponse(val t: Throwable) : LocalCompletedResponse()
}
