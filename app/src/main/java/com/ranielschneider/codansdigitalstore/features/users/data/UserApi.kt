package com.ranielschneider.codansdigitalstore.features.users.data
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users?limit=0")
    suspend fun getUsers(): UserResponseDto

    @GET ("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): UserDto

}