package com.mikhailapps.architecture.domain.repository

import com.mikhailapps.architecture.domain.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected fun <T> doRequest(
        doSomethingInSuccess: ((T) -> Unit)? = null,
        request: suspend () -> T
    ) = flow {
        request().also { data ->
            doSomethingInSuccess?.invoke(data)
            emit(Resource.success(data))
        }
    }.flowOn(dispatcher).catch { exception ->
        emit(Resource.failure(throwable = exception))
    }
}