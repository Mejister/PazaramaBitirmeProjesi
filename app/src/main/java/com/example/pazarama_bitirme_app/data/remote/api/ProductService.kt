package com.example.pazarama_bitirme_app.data.remote.api

import com.example.pazarama_bitirme_app.data.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products/{product_id}")
    suspend fun getProductDetail(@Path("product_id") productId:Int) : Response<Product>
}