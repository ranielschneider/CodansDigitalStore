package com.ranielschneider.codansdigitalstore.features.cart.presentation.details

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.ranielschneider.codansdigitalstore.features.cart.domain.Cart
import com.ranielschneider.codansdigitalstore.ui.theme.BackgroundLight
import com.ranielschneider.codansdigitalstore.ui.theme.PurpleDark
import com.ranielschneider.codansdigitalstore.ui.theme.PurplePrimary


@Composable
fun CartDetailsScreen(
    cart: Cart?,
    onBackClick: () -> Unit = {},

) {
    // Mantém a barra de status com o tom escuro padronizado
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = PurpleDark.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    CartDetailsContent(
        cart = cart,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDetailsContent(
    cart: Cart?,
    onBackClick: () -> Unit = {}
) {
    //val formatterPrice = remember { MoedaFormatacao() }

    // Utiliza Scaffold para padronizar o TopBar exatamente igual à listagem de carrinhos
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0E8FF))
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = if (cart != null) "Cart #${cart.idCart}" else "Detalhes do Carrinho",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Return",
                                tint = Color.Black
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundLight)
        ) {
            if (cart == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No cart Selected",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Cart",
                        tint = PurplePrimary,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(bottom = 16.dp)
                    )

                    Text(
                        text = "### Product Details (${cart.productsCart.size} itens) ###",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )

                    val textoDetalhes = remember(cart) {
                        gerarConteudoProdutos(cart)
                    }

                    Text(
                        text = textoDetalhes,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

private fun gerarConteudoProdutos(cart: Cart): String {
    val detalhes = StringBuilder()

    cart.productsCart.forEachIndexed { index, product ->
        val num = index + 1
        detalhes.append("#$num - ID: ${product.id}\n")
        detalhes.append(" - Product: ${product.title}\n")
        detalhes.append(" - Qty: ${product.quantity}\n")
        detalhes.append(" - Price/Unit: ${product.price}\n")
        detalhes.append(" - Subtotal: ${product.total}\n\n")
    }

    detalhes.append("----------------------------\n")
    detalhes.append("TOTAL: ${cart.totalCart}\n\n")

    return detalhes.toString()
}

@Preview(showBackground = true)
@Composable
fun CartDetailsScreenPreview() {
    MaterialTheme {
        CartDetailsContent(
            cart = Cart(
                idCart = 1,
                totalCart = 12989.73,
                totalProductsCart = 4,
                productsCart = emptyList()
            )
        )
    }
}