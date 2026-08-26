package com.ranielschneider.codansdigitalstore.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ranielschneider.codansdigitalstore.ui.theme.CodansDigitalStoreTheme

@Composable
fun HomeScreen(
    onProductsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeButton(
                text = "Produtos",
                modifier = Modifier.weight(1f),
                onClick = onProductsClick
            )

            HomeButton(
                text = "Carrinho",
                modifier = Modifier.weight(1f),
                onClick = {}
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeButton(
                text = "Usuários",
                modifier = Modifier.weight(1f),
                onClick = {}
            )

            HomeButton(
                text = "Postagens",
                modifier = Modifier.weight(1f),
                onClick = {}
            )
        }
    }
}

@Composable
fun HomeButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(95.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF751F),
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CodansDigitalStoreTheme {
        HomeScreen(
            onProductsClick = {}
        )
    }
}