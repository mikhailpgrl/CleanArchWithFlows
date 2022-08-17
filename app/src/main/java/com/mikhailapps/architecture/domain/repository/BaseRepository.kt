package com.mikhailapps.architecture.domain.repository

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.local.LocalCompletedResponse
import com.mikhailapps.architecture.domain.local.LocalResponse
import com.mikhailapps.architecture.domain.local.response.toResource
import com.mikhailapps.architecture.domain.remote.response.RemoteResponse
import com.mikhailapps.architecture.domain.remote.response.toResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected fun <T> doLocalRequest(
        request: suspend () -> LocalResponse<T>
    ) = flow {
        request().also { data ->
            emit(data.toResource())
        }
    }.flowOn(dispatcher).catch { exception ->
        emit(Resource.failure(throwable = exception))
    }

    protected fun doCompletableLocalRequest(
        request: suspend () -> LocalCompletedResponse
    ) = flow {
        request().also { response ->
            emit(response.toResource())
        }
    }.flowOn(dispatcher).catch { exception ->
        emit(Resource.failure(throwable = exception))
    }

    protected fun <T, U> doRemoteRequest(
        request: suspend () -> RemoteResponse<T, U>
    ) = flow {
        request().also { data ->
            emit(data.toResource())
        }
    }.flowOn(dispatcher).catch { exception ->
        emit(Resource.failure(throwable = exception))
    }
}