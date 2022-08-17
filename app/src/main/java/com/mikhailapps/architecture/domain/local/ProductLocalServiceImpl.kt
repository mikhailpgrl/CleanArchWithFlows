package com.mikhailapps.architecture.domain.local

import com.mikhailapps.architecture.domain.local.dao.ProductDao
import com.mikhailapps.architecture.domain.local.mapper.ProductLocalMapper
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import javax.inject.Inject

class ProductLocalServiceImpl @Inject constructor(
    private val productDao: ProductDao,
    private val mapper: ProductLocalMapper
) : ProductLocalService {

    override suspend fun getProducts(): LocalResponse<List<ProductDomainModel>> {
        return safeExecute {
            productDao.getProducts().map {
                mapper.to(it)
            }
        }
    }

    override suspend fun insertProduct(product: ProductDomainModel): LocalCompletedResponse {
        return safeExecuteCompletable {
            productDao.insertProduct(mapper.from(product))
        }
    }
}