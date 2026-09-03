package com.ranielschneider.codansdigitalstore.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ranielschneider.codansdigitalstore.features.cart.presentation.CartScreen
import com.ranielschneider.codansdigitalstore.features.cart.presentation.CartViewModel
import com.ranielschneider.codansdigitalstore.features.cart.presentation.details.CartDetailsScreen
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
                },
                viewModel = hiltViewModel(),
                onCartClick = { cartId ->
                    navController.navigate("cart_detail/$cartId")
                }
            )
        }

        // 2. Nova rota adicionada para a tela de Detalhes do Carrinho com argumento
        composable(
            route = "cart_detail/{cartId}",
            arguments = listOf(
                navArgument("cartId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            val cartId = backStackEntry.arguments?.getInt("cartId") ?: 0

            val sharedCartViewModel: CartViewModel = hiltViewModel()
            val carts by sharedCartViewModel.carts.collectAsStateWithLifecycle()
            val cartSelected = carts.find { it.idCart == cartId }

            CartDetailsScreen(
                cart = cartSelected,
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