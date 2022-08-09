package com.mikhailapps.architecture.domain.usecase

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCaseImpl(private val productRepository: ProductRepository) : GetProductUseCase {
    
    override fun execute(): Flow<Resource<ProductDomainModel>> {
        return productRepository.getRemoteProducts()
    }
}