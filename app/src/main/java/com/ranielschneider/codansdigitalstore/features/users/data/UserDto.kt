package com.ranielschneider.codansdigitalstore.features.users.data

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,

    @SerializedName("firstName")
    val primeiroNome: String,

    @SerializedName("lastName")
    val ultimoNome: String,

    val email: String,
    val image: String
)