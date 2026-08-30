package com.ranielschneider.codansdigitalstore.features.products.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel
) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    Column {
        products.forEach { product ->
            Text(
                text = product.title
            )
        }
    }
}