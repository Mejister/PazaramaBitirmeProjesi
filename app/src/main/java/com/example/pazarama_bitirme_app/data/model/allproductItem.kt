package com.example.pazarama_bitirme_app.data.model


import com.google.gson.annotations.SerializedName

data class allproductItem(
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
    val rating: RatingX?,
    @SerializedName("title")
    val title: String?
)