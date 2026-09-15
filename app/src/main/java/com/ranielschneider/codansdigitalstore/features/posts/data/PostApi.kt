package com.ranielschneider.codansdigitalstore.features.posts.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): PostResponseDto

    @GET("posts/{id}")
    suspend fun getPostById(
        @Path("id") id: Int
    ): PostDto

}