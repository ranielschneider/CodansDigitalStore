package com.ranielschneider.codansdigitalstore.features.products.domain

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product
}