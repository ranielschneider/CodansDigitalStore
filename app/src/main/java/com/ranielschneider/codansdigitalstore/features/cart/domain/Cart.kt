package com.ranielschneider.codansdigitalstore.features.cart.domain

import java.io.Serializable

data class Cart(
    val idCart: Int,
    val totalCart: Double,
    val totalProductsCart: Int,
    val productsCart: List<ProductCartItem> = emptyList()
): Serializable


data class ProductCartItem(
    val id: Int,
    val title: String,
    val price: Double,
    val total: Double,
    val quantity: Int
): Serializable