package com.mikhailapps.architecture.di

import com.mikhailapps.architecture.domain.local.ProductLocalService
import com.mikhailapps.architecture.domain.local.ProductLocalServiceImpl
import com.mikhailapps.architecture.domain.remote.serivce.product.ProductRemoteService
import com.mikhailapps.architecture.domain.remote.serivce.product.ProductRemoteServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun bindProductLocalService(productLocalService: ProductLocalServiceImpl): ProductLocalService

    @Binds
    abstract fun bindProductRemoteService(productRemoteService: ProductRemoteServiceImpl): ProductRemoteService

}