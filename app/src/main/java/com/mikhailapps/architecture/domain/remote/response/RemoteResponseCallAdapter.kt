package com.mikhailapps.architecture.domain.remote.response

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class RemoteResponseCallAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<RemoteResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<RemoteResponse<S, E>> {
        return RemoteResponseCall(call, errorBodyConverter)
    }
}