package com.mikhailapps.architecture.domain.repository

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun refreshProducts(): Flow<Resource<ProductDomainModel>>

    fun getRemoteProducts(): Flow<Resource<ProductDomainModel>>


}