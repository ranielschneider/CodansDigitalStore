package com.ranielschneider.codansdigitalstore.features.products.data

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)