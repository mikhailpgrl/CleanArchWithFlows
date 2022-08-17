package com.mikhailapps.architecture.domain.remote.serivce.product

import com.mikhailapps.architecture.domain.remote.response.RemoteResponse
import com.mikhailapps.architecture.domain.remote.serivce.product.dto.ProductDto

interface ProductApi {

    suspend fun getProducts(): RemoteResponse<List<ProductDto>, Any> {
        return RemoteResponse.Success(listOf(ProductDto("id", "3")))
    }
}