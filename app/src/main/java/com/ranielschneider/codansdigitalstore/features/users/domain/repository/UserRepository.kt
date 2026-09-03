package com.ranielschneider.codansdigitalstore.features.users.domain.repository

import com.ranielschneider.codansdigitalstore.features.users.domain.User

interface UserRepository {

     suspend fun getUsers(): List<User>
}