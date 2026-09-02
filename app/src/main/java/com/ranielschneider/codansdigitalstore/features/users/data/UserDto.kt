package com.ranielschneider.codansdigitalstore.features.users.data

data class UserDto (
    val id: Int,
    val primeiroNome: String,
    val ultimoNome: String,
    val email: String,
    val image: Int
)
