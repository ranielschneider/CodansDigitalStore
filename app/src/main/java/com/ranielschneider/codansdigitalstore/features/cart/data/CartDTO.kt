package com.ranielschneider.codansdigitalstore.features.cart.data

import com.ranielschneider.codansdigitalstore.features.cart.domain.Cart
import com.ranielschneider.codansdigitalstore.features.cart.domain.ProductCartItem

data class CartDTO(
    val discountedTotal: Double,
    val id: Int,
    val products: List<ProductCart>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)

fun CartDTO.toCart(): Cart {
    return Cart(
        idCart = this.id,
        totalCart = this.total,
        totalProductsCart = this.totalProducts,
        productsCart = this.products.map { it.toProductCartItem() }
    )
}

