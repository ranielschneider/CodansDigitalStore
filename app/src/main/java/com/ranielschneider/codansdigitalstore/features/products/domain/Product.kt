package com.ranielschneider.codansdigitalstore.features.products.domain

import java.io.Serializable

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
): Serializable