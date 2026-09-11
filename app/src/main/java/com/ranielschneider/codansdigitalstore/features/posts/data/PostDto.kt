package com.ranielschneider.codansdigitalstore.features.posts.data

data class PostDto(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)
