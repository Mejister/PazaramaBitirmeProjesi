package com.example.pazarama_bitirme_app.data.remote.source.impl

import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.api.ProductService
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(private val productService: ProductService) :
    BaseRemoteDataSource(), ProductRemoteDataSource {
    override suspend fun getProductDetail(productId: Int): Flow<DataState<Product>> {
        return getResult { productService.getProductDetail(productId) }
    }
}