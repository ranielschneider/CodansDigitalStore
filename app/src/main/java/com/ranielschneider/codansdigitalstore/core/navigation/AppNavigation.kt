package com.ranielschneider.codansdigitalstore.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ranielschneider.codansdigitalstore.features.home.presentation.HomeScreen
import com.ranielschneider.codansdigitalstore.features.products.presentation.ProductDetailScreen
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
                viewModel = hiltViewModel(),
                onProductClick = { productId ->
                    navController.navigate("product/$productId")
                }
            )
        }

        composable(
            route = "product/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) {
            ProductDetailScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}