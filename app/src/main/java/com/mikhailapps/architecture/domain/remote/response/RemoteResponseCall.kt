package com.mikhailapps.architecture.domain.remote.response


import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class RemoteResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<RemoteResponse<S, E>> {

    override fun enqueue(callback: Callback<RemoteResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@RemoteResponseCall,
                            Response.success(RemoteResponse.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@RemoteResponseCall,
                            Response.success(RemoteResponse.UnknownError(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }

                    if (errorBody != null) {
                        callback.onResponse(
                            this@RemoteResponseCall,
                            Response.success(RemoteResponse.ApiError(errorBody, code))
                        )
                    } else {
                        callback.onResponse(
                            this@RemoteResponseCall,
                            Response.success(RemoteResponse.UnknownError(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val remoteResponse = when (throwable) {
                    is IOException -> RemoteResponse.NetworkError(throwable)
                    else -> {
                        RemoteResponse.UnknownError(throwable)
                    }
                }
                callback.onResponse(this@RemoteResponseCall, Response.success(remoteResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = RemoteResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<RemoteResponse<S, E>> {
        throw UnsupportedOperationException("RemoteResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}