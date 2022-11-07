package com.example.pazarama_bitirme_app.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String?
)

data class ProductDTO(
    val id: Int?,
    val categories: String?,
    val description: String?,
    val image: String?,
    val price: Double?,
    val rating: Rating?,
    val title: String?
)