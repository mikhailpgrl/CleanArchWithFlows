package com.mikhailapps.architecture.domain.local

import com.mikhailapps.architecture.domain.local.LocalCompletedResponse.LocalCompletedErrorResponse
import com.mikhailapps.architecture.domain.local.LocalCompletedResponse.LocalCompletedSuccessResponse

inline fun <T> safeExecute(operation: () -> T): LocalResponse<T> {
    return try {
        val result = operation()
        LocalResponse.LocalSuccessResponse(result)
    } catch (t: Throwable) {
        LocalResponse.LocalErrorResponse(t)
    }
}
inline fun safeExecuteCompletable(crossinline operate: () -> Unit): LocalCompletedResponse {
    return try {
        operate()
        LocalCompletedSuccessResponse
    } catch (t: Throwable) {
        LocalCompletedErrorResponse(t)
    }
}
