package com.ranielschneider.codansdigitalstore.features.posts.domain.repository

import com.ranielschneider.codansdigitalstore.features.posts.domain.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}