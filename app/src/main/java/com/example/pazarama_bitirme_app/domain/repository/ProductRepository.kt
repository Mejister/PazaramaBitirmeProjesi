package com.example.pazarama_bitirme_app.domain.repository

import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductDetail(productId: Int): Flow<DataState<Product>>
}