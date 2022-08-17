package com.mikhailapps.architecture.domain.remote

import com.mikhailapps.architecture.domain.remote.response.RemoteResponse

abstract class BaseRemoteService {

    protected suspend fun <Dto, U, DomainModel> executeRequest(
        request: suspend () -> RemoteResponse<Dto, U>,
        transform: (Dto) -> DomainModel
    ): RemoteResponse<DomainModel, U> {
        return when (val response = request.invoke()) {
            is RemoteResponse.Success -> RemoteResponse.Success(transform.invoke(response.body))
            is RemoteResponse.ApiError -> response
            is RemoteResponse.NetworkError -> response
            is RemoteResponse.UnknownError -> response
        }
    }

}