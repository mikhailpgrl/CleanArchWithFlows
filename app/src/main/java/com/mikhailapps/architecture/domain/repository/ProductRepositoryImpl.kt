package com.mikhailapps.architecture.domain.repository

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() :
    BaseRepository(),
    ProductRepository {

    override fun refreshProducts(): Flow<Resource<ProductDomainModel>> {
        return doRequest(request = {
            ProductDomainModel(
                "id", "product"
            )
        })
    }

    override fun getRemoteProducts(): Flow<Resource<ProductDomainModel>> {
        return doRequest(request = {
            ProductDomainModel(
                "id", "product"
            )
        })
    }

}