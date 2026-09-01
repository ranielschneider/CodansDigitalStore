package com.ranielschneider.codansdigitalstore.features.products.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val product by viewModel.product.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProduct()
    }

    if (product == null) {
        Text(
            text = "Carregando..."
        )
    } else {
        Text(
            text = product!!.title
        )
    }
}