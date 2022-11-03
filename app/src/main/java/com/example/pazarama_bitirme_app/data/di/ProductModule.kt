package com.example.pazarama_bitirme_app.data.di

import com.example.pazarama_bitirme_app.data.remote.api.ProductService
import com.example.pazarama_bitirme_app.data.remote.source.ProductRemoteDataSource
import com.example.pazarama_bitirme_app.data.remote.source.impl.ProductRemoteDataSourceImpl
import com.example.pazarama_bitirme_app.domain.repository.ProductRepository
import com.example.pazarama_bitirme_app.domain.repository.impl.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton



    @Module
    @InstallIn(SingletonComponent::class)
    class ProductModule {

        @Singleton
        @Provides
        fun provideProductService(retrofit: Retrofit) = retrofit.create(ProductService::class.java)

        @Singleton
        @Provides
        fun provideProductRemoteDataSource(ProductService: ProductService): ProductRemoteDataSource =
            ProductRemoteDataSourceImpl(ProductService)

        @Singleton
        @Provides
        fun provideProductRepository(ProductRemoteDataSource: ProductRemoteDataSource): ProductRepository =
            ProductRepositoryImpl(ProductRemoteDataSource)
    }
