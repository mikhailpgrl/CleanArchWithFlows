package com.mikhailapps.architecture.domain.remote.response

sealed class RemoteResponseThrowable : Throwable() {

    data class ApiErrorThrowable(val body: String, val code: Int) : RemoteResponseThrowable()

    object NetworkErrorThrowable : RemoteResponseThrowable()

    object UnknownErrorThrowable : RemoteResponseThrowable()
}