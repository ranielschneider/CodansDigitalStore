package com.ranielschneider.codansdigitalstore.features.cart.domain

interface CartRepository {

    suspend fun getCarts(): List<Cart>
}