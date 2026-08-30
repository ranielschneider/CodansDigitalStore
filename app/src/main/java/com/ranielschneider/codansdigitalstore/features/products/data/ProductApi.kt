package com.ranielschneider.codansdigitalstore.features.products.data

import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductsResponseDto
}