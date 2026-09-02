package com.ranielschneider.codansdigitalstore.features.users.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepositoryImpl

@Composable
fun UserScreen(
    viewmodel: UserViewModel
) {
    val repository = UserRepositoryImpl(
        api = //a fazer
    )
    LaunchedEffect(Unit) {
        viewmodel.getUsers()
    }

    Column() {
        viewmodel.users.forEach {  user ->
            Text(
                text = user.nome
            )
        }
    }
}