package com.example.pazarama_bitirme_app.data.remote.api

import com.example.pazarama_bitirme_app.data.model.Categories
import com.example.pazarama_bitirme_app.data.model.Product
import com.example.pazarama_bitirme_app.data.remote.utils.DataState
import retrofit2.Response
import retrofit2.http.GET


interface ProductService {


    @GET("products/categories")
    suspend fun getAllCategories():Response<Categories>

    @GET("products")
    suspend fun getProductList() : Response<List<Product>>
}