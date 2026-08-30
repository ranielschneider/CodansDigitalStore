package com.ranielschneider.codansdigitalstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ranielschneider.codansdigitalstore.core.navigation.AppNavigation
import com.ranielschneider.codansdigitalstore.ui.theme.CodansDigitalStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
           CodansDigitalStoreTheme {
               AppNavigation()
           }
        }
    }
}


