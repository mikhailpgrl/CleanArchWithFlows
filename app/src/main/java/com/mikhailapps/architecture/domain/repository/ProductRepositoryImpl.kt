package com.mikhailapps.architecture.domain.repository

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.local.ProductLocalService
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.remote.serivce.product.ProductRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productRemoteService: ProductRemoteService,
    private val productLocalService: ProductLocalService
) : BaseRepository(), ProductRepository {

    override fun getRemoteProducts(): Flow<Resource<List<ProductDomainModel>>> {
        return doRemoteRequest {
            productRemoteService.getProducts()
        }
    }

    override fun getLocalProducts(): Flow<Resource<List<ProductDomainModel>>> {
        return doLocalRequest {
            productLocalService.getProducts()
        }
    }

    override fun insertProduct(productDomainModel: ProductDomainModel): Flow<Resource<Nothing>> {
        return doCompletableLocalRequest {
            productLocalService.insertProduct(productDomainModel)
        }
    }

}