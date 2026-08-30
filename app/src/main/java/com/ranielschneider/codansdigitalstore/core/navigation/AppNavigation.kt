package com.ranielschneider.codansdigitalstore.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ranielschneider.codansdigitalstore.features.home.presentation.HomeScreen
import com.ranielschneider.codansdigitalstore.features.products.presentation.ProductsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                onProductsClick = {
                    navController.navigate("products")
                }
            )
        }

        composable("products") {
            ProductsScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}