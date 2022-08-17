package com.mikhailapps.architecture.domain.remote.serivce.product

import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.remote.BaseRemoteService
import com.mikhailapps.architecture.domain.remote.response.RemoteResponse
import com.mikhailapps.architecture.domain.remote.serivce.product.mapper.ProductRemoteMapper
import javax.inject.Inject

class ProductRemoteServiceImpl @Inject constructor(
    private val api: ProductApi,
    private val remoteMapper: ProductRemoteMapper
) : BaseRemoteService(), ProductRemoteService {
    override suspend fun getProducts(): RemoteResponse<List<ProductDomainModel>, Any> {
        return executeRequest(request = {
            api.getProducts()
        }, transform = {
            it.map { remoteMapper.from(it) }
        })
    }
}