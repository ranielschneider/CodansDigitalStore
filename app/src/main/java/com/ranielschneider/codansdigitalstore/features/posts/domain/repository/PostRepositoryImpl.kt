package com.ranielschneider.codansdigitalstore.features.posts.domain.repository

import com.ranielschneider.codansdigitalstore.features.posts.data.PostApi
import com.ranielschneider.codansdigitalstore.features.posts.data.toDomain
import com.ranielschneider.codansdigitalstore.features.posts.domain.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostApi
) : PostRepository  {

    override suspend fun getPosts(): List<Post> {
        return api.getPosts()
            .posts
            .map { it.toDomain() }
    }
}