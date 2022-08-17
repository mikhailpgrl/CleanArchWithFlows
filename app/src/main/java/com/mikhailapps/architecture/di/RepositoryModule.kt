package com.mikhailapps.architecture.di

import com.mikhailapps.architecture.domain.local.mapper.ProductLocalMapper
import com.mikhailapps.architecture.domain.remote.serivce.product.mapper.ProductRemoteMapper
import com.mikhailapps.architecture.domain.repository.ProductRepository
import com.mikhailapps.architecture.domain.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}