package com.mikhailapps.architecture.domain.repository

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getRemoteProducts(): Flow<Resource<List<ProductDomainModel>>>

    fun getLocalProducts(): Flow<Resource<List<ProductDomainModel>>>

    fun insertProduct(productDomainModel: ProductDomainModel): Flow<Resource<Nothing>>
}