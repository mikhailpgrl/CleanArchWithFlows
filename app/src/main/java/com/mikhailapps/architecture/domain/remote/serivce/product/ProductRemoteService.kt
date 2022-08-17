package com.mikhailapps.architecture.domain.remote.serivce.product

import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.remote.response.RemoteResponse

interface ProductRemoteService {
    suspend fun getProducts(): RemoteResponse<List<ProductDomainModel>, Any>
}