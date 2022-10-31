package com.example.pazarama_bitirme_app.data.model


import com.google.gson.annotations.SerializedName

data class RatingX(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)