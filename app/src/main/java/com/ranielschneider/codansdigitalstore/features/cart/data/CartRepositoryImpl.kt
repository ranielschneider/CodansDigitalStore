package com.ranielschneider.codansdigitalstore.features.cart.data

import com.ranielschneider.codansdigitalstore.features.cart.domain.Cart
import com.ranielschneider.codansdigitalstore.features.cart.domain.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApi: CartApi
): CartRepository {

    override suspend fun getCarts(): List<Cart> {
        return try {
            val response = cartApi.getCarts()
            if (response.isSuccessful) {
                response.body()?.carts?.map { it.toCart() } ?: emptyList()
            } else {
                emptyList()
            }

        }catch (e: Exception){
            emptyList()
        }
    }
}