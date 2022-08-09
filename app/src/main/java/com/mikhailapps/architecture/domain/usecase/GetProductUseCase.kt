package com.mikhailapps.architecture.domain.usecase

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {

    fun execute(): Flow<Resource<ProductDomainModel>>
}