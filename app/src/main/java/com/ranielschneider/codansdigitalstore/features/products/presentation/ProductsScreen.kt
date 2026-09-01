package com.ranielschneider.codansdigitalstore.features.products.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel,
    onProductClick: (Int) -> Unit
) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductCard(
                title = product.title,
                price = product.price,
                thumbnail = product.thumbnail,
                onClick = {
                    onProductClick(product.id)
                }
            )
        }
    }
}

@Composable
fun ProductCard(
    title: String,
    price: Double,
    thumbnail: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = thumbnail,
                contentDescription = title,
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = title)
                Text(text = "€ $price")
            }
        }
    }
}