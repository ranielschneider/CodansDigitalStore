package com.ranielschneider.codansdigitalstore.features.posts.data

import com.ranielschneider.codansdigitalstore.features.posts.domain.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}