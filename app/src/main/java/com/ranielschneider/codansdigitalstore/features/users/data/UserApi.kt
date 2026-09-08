package com.ranielschneider.codansdigitalstore.features.users.data
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): UserResponseDto

}