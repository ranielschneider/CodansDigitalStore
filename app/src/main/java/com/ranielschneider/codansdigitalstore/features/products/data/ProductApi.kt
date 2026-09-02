package com.ranielschneider.codansdigitalstore.features.products.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("productCarts")
    suspend fun getProducts(): ProductsResponseDto

    @GET("productCarts/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto
}