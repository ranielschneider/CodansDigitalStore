package com.ranielschneider.codansdigitalstore.features.users.data

import com.ranielschneider.codansdigitalstore.features.users.domain.User


    fun UserDto.toDomain(): User {
        return User(
            id = id,
            nome = "$primeiroNome $ultimoNome",
            email = email,
            image = image

        )
    }
