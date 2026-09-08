package com.ranielschneider.codansdigitalstore.features.users.data

data class UserResponseDto (
    val users: List<UserDto>,
    val total: Int,
    val skip: Int,
    val limit: Int

)

