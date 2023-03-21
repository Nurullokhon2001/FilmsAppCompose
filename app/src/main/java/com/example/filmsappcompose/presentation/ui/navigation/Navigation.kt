package com.example.filmsappcompose.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.filmsappcompose.MainActivity
import com.example.filmsappcompose.data.local.MoviesDb
import com.example.filmsappcompose.data.local.db.MoviesLocalDataSource
import com.example.filmsappcompose.data.remote.network.MoviesRemoteDataSource
import com.example.filmsappcompose.data.remote.network.RetrofitClient
import com.example.filmsappcompose.data.repository.RepositoryImpl
import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.use_case.*
import com.example.filmsappcompose.presentation.details_screen.DetailsScreen
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModel
import com.example.filmsappcompose.presentation.main_screen.MainScreen
import com.example.filmsappcompose.presentation.main_screen.MainScreenViewModel
import com.example.filmsappcompose.utiils.viewModelFactory

@Composable
fun Navigation(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.routes
    ) {

//        composable(Routes.MainScreen.routes) {
//            MainScreen(navController = navController, mainScreenViewModel)
//        }
//        composable("${Routes.DetailsScreen.routes}/{filmId}") {
//            it.arguments?.let { arguments ->
//
//                val detailsViewModel: DetailsViewModel =
//                    viewModel(factory = viewModelFactory {
//                        DetailsViewModel(
//                            getMovieDetailsUseCase,
//                            getMovieActorsUseCase,
//                            arguments.getString("filmId")!!.toInt()
//                        )
//                    })
//                DetailsScreen(
//                    detailsViewModel,
//                )
//            }
//        }
    }
}