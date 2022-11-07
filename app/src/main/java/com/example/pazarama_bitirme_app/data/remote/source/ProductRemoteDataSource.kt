package com.example.pazarama_bitirme_app.data.remote.source

import com.example.pazarama_bitirme_app.data.model.Categories
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductRemoteDataSource {

        suspend fun getAllCategories(): Flow<DataState<Categories>>
        suspend fun getProductList () : Flow<DataState<List<Product>>>
}

