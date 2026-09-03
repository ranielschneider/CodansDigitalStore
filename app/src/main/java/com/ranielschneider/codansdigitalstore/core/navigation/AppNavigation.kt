package com.ranielschneider.codansdigitalstore.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ranielschneider.codansdigitalstore.features.cart.presentation.CartScreen
import com.ranielschneider.codansdigitalstore.features.home.presentation.HomeScreen
import com.ranielschneider.codansdigitalstore.features.products.presentation.ProductDetailScreen
import com.ranielschneider.codansdigitalstore.features.products.presentation.ProductsScreen
import com.ranielschneider.codansdigitalstore.features.users.presentation.UserScreen

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
                    navController.navigate("productCarts")
                },
                onUserClick = {
                    navController.navigate("users")
                },
                onCartClick = {
                    navController.navigate("cart")
                }
            )
        }
        composable("users") {
            UserScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("productCarts") {
            ProductsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = hiltViewModel(),
                onProductClick = { productId ->
                    navController.navigate("product/$productId")
                }
            )
        }
        composable("cart") {
            CartScreen(
                onBackClick = {
                    navController.popBackStack()
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
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = hiltViewModel()
            )
        }
    }
}