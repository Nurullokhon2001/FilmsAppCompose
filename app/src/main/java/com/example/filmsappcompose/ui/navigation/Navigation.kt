package com.example.filmsappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.filmsappcompose.main_screen.MainScreen
import com.example.filmsappcompose.main_screen.MainScreenViewModel

@Composable
fun Navigation(navController: NavHostController, mainScreenViewModel: MainScreenViewModel) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.routes
    ) {
        composable(Routes.MainScreen.routes) {
            MainScreen(navController = navController, mainScreenViewModel)
        }
    }
}