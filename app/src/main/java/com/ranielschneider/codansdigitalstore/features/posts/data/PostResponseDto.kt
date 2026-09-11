package com.ranielschneider.codansdigitalstore.features.posts.data

data class PostResponseDto(
    val posts: List<PostDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
