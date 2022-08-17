package com.mikhailapps.architecture.domain.usecase

import com.mikhailapps.architecture.domain.Resource
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetRemoteProductUseCase {

    override operator fun invoke(): Flow<Resource<List<ProductDomainModel>>> {
        return productRepository.getRemoteProducts()
    }
}