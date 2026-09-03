package com.ranielschneider.codansdigitalstore.features.cart.data

import com.ranielschneider.codansdigitalstore.features.cart.domain.ProductCartItem

data class ProductCart (
    val discountPercentage: Double,
    val discountedTotal: Double,
    val id: Int,
    val price: Double,
    val quantity: Int,
    val thumbnail: String,
    val title: String,
    val total: Double
)
fun ProductCart.toProductCartItem(): ProductCartItem {
    return ProductCartItem(
        id = this.id,
        title = this.title,
        price = this.price,
        total = this.total,
        quantity = this.quantity
    )
}