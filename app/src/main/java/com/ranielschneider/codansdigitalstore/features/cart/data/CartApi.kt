package com.ranielschneider.codansdigitalstore.features.cart.data

import retrofit2.Response
import retrofit2.http.GET

interface CartApi {

    //Cart: https://dummyjson.com/carts

    @GET("carts")
    suspend fun getCarts(): Response<CartResponseDTO>

}