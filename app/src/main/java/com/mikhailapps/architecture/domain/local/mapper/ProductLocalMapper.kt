package com.mikhailapps.architecture.domain.local.mapper

import com.mikhailapps.architecture.domain.local.entity.ProductEntity
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import javax.inject.Inject

class ProductLocalMapper @Inject constructor(): LocalMapper<ProductDomainModel, ProductEntity> {

    override fun from(domain: ProductDomainModel): ProductEntity {
        return ProductEntity(domain.id, domain.name)
    }

    override fun to(entity: ProductEntity): ProductDomainModel {
        return ProductDomainModel(entity.id, entity.name)
    }
}