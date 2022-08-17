package com.mikhailapps.architecture.domain.remote.serivce.product.mapper

import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.remote.mapper.RemoteMapper
import com.mikhailapps.architecture.domain.remote.serivce.product.dto.ProductDto
import javax.inject.Inject

class ProductRemoteMapper @Inject constructor() : RemoteMapper<ProductDto, ProductDomainModel> {
    override fun from(dto: ProductDto): ProductDomainModel {
        return ProductDomainModel(dto.id, dto.name)
    }

    override fun to(domain: ProductDomainModel): ProductDto {
        return ProductDto(domain.id, domain.name)
    }
}