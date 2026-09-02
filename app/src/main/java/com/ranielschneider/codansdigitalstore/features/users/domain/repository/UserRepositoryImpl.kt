package com.ranielschneider.codansdigitalstore.features.users.domain.repository

import com.ranielschneider.codansdigitalstore.features.users.data.UserApi
import com.ranielschneider.codansdigitalstore.features.users.data.toDomain
import com.ranielschneider.codansdigitalstore.features.users.domain.User

class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUsers(): List<User>{
        return api.getUsers()
            .users
            .map { it.toDomain() }
    }

}