package com.example.filmsappcompose.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.filmsappcompose.presentation.details_screen.DetailsScreen
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModel
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModelAssistedFactory
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModelFactory
import com.example.filmsappcompose.presentation.main_screen.MainScreen
import com.example.filmsappcompose.presentation.main_screen.MainScreenViewModel
import com.example.filmsappcompose.presentation.main_screen.MainViewModelFactory

@Composable
fun Navigation(
    navController: NavHostController,
    mainViewModelFactory: MainViewModelFactory,
    detailsViewModelAssistedFactory: DetailsViewModelAssistedFactory
) {
    NavHost(
        navController = navController, startDestination = Routes.MainScreen.routes
    ) {
        composable(Routes.MainScreen.routes) {
            val mainScreenViewModel: MainScreenViewModel = viewModel(
                factory = mainViewModelFactory,
            )
            MainScreen(navController = navController, mainScreenViewModel)
        }
        composable("${Routes.DetailsScreen.routes}/{filmId}") {
            it.arguments?.let { arguments ->
                val detailsViewModel: DetailsViewModel = viewModel(
                    factory = DetailsViewModelFactory(
                        detailsViewModelAssistedFactory, arguments.getString("filmId")!!.toInt()
                    )
                )
                DetailsScreen(
                    detailsViewModel,
                )
            }
        }
    }
}