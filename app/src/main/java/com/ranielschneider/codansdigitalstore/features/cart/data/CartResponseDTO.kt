package com.ranielschneider.codansdigitalstore.features.cart.data

data class CartResponseDTO (
    val carts: List<CartDTO>,
    val limit: Int,
    val skip: Int,
    val total: Int
)