package com.ranielschneider.codansdigitalstore.features.posts.data

import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): PostResponseDto
}