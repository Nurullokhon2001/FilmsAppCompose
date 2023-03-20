package com.example.filmsappcompose.presentation.ui.navigation


sealed class Routes(val routes: String) {
    object MainScreen : Routes("MainScreen")
    object DetailsScreen : Routes("DetailsScreen")
}
