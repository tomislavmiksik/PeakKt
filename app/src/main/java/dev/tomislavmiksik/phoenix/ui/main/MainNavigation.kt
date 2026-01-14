package dev.tomislavmiksik.phoenix.ui.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object MainGraph

@Serializable
data object MainRoute

@Serializable
data object DashboardRoute

@Serializable
data object ProfileRoute

fun NavGraphBuilder.mainGraph() {
    navigation<MainGraph>(
        startDestination = MainRoute,
    ) {
        composable<MainRoute> {
            MainScreen()
        }
    }
}
