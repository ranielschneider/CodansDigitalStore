package com.ranielschneider.codansdigitalstore.features.cart.presentation

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ranielschneider.codansdigitalstore.features.cart.domain.Cart
import com.ranielschneider.codansdigitalstore.ui.theme.BackgroundLight
import com.ranielschneider.codansdigitalstore.ui.theme.PurpleDark
import com.ranielschneider.codansdigitalstore.ui.theme.PurplePrimary

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    onCartClick: (cartId: Int) -> Unit = {}
) {
    val carts by viewModel.carts.collectAsStateWithLifecycle()

    // Pega a activity atual para pintar a barra de status com a cor secundária
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Converte a cor do Compose (AppColors.SecondaryBlue) para o inteiro do Android usando .toArgb()
            window.statusBarColor = PurpleDark.toArgb()

            // Define se os ícones da barra de status (relógio/bateria) ficam claros (false) ou escuros (true)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    CartContent(
        carts = carts,
        onCartClick = onCartClick
    )
}

@Composable
fun CartContent(
    carts: List<Cart>,
    onCartClick: (cartId: Int) -> Unit = {}
) {
    //val formatador = remember { MoedaFormatacao() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight) // Usando a variável de fundo
    ) {
        // Barra superior fixa
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(PurplePrimary) // Usando a variável azul principal
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cart",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }

        when {
            carts.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No cart found",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(carts) { cart ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCartClick(cart.idCart)
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCart,
                                    contentDescription = "Cart",
                                    tint = PurplePrimary, // Usando a variável azul principal
                                    modifier = Modifier
                                        .size(55.dp)
                                        .padding(end = 8.dp)
                                )

                                Column {
                                    Text(
                                        text = "Cart ID: ${cart.idCart}",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        //text = "Total: ${formatador.formatToBRL(cart.totalCart)}",
                                        text = "Total: ${cart.totalCart}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "Items: ${cart.totalProductsCart}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    MaterialTheme {
        CartContent(
            carts = listOf(
                Cart(
                    idCart = 101,
                    totalCart = 120.00,
                    totalProductsCart = 2,
                    productsCart = emptyList()
                ),
                Cart(
                    idCart = 102,
                    totalCart = 450.50,
                    totalProductsCart = 4,
                    productsCart = emptyList()
                ),
                Cart(
                    idCart = 103,
                    totalCart = 250.50,
                    totalProductsCart = 5,
                    productsCart = emptyList()
                )
            )
        )
    }
}