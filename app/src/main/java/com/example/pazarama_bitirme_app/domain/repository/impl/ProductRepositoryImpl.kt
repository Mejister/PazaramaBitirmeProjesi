package com.example.pazarama_bitirme_app.domain.repository.impl

import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.source.ProductRemoteDataSource
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import com.example.pazarama_bitirme_app.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


    class ProductRepositoryImpl  @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource) :
        ProductRepository {
        override suspend fun getProductDetail(productId: Int): Flow<DataState<Product>> {
            return productRemoteDataSource.getProductDetail(productId)
        }
    }
