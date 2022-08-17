package com.mikhailapps.architecture.domain.local

import com.mikhailapps.architecture.domain.model.ProductDomainModel

interface ProductLocalService {

    suspend fun getProducts(): LocalResponse<List<ProductDomainModel>>

    suspend fun insertProduct(product: ProductDomainModel): LocalCompletedResponse
}