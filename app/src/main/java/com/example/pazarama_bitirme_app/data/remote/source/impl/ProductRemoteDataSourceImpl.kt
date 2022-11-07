package com.example.pazarama_bitirme_app.data.remote.source.impl

import com.example.pazarama_bitirme_app.data.model.Categories
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.api.ProductService
import com.example.pazarama_bitirme_app.data.remote.source.ProductRemoteDataSource
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(private val productService: ProductService) :
    BaseRemoteDataSource(), ProductRemoteDataSource {
    override suspend fun getAllCategories(): Flow<DataState<Categories>> {
        return getResult { productService.getAllCategories() }
    }

    override suspend fun getProductList(): Flow<DataState<List<Product>>> {
        return getResult {
            productService.getProductList()
        }
    }
}