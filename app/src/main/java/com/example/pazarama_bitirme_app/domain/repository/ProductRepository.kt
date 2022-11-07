package com.example.pazarama_bitirme_app.domain.repository

import com.example.pazarama_bitirme_app.data.model.Categories
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {
    suspend fun getAllCategories(): Flow<DataState<Categories>>
    suspend fun getProductList(): Flow<DataState<List<Product>>>
}