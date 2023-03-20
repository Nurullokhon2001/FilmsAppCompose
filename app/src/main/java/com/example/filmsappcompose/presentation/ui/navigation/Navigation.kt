package com.example.filmsappcompose.presentation.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.filmsappcompose.data.remote.MoviesRemoteDataSource
import com.example.filmsappcompose.data.repository.RepositoryImpl
import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.use_case.GetMovieActorsUseCase
import com.example.filmsappcompose.domain.use_case.GetMovieDetailsUseCase
import com.example.filmsappcompose.domain.use_case.GetPopularMoviesUseCase
import com.example.filmsappcompose.domain.use_case.SearchMoviesUseCase
import com.example.filmsappcompose.presentation.details_screen.DetailsScreen
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModel
import com.example.filmsappcompose.presentation.main_screen.MainScreen
import com.example.filmsappcompose.presentation.main_screen.MainScreenViewModel
import com.example.filmsappcompose.utiils.viewModelFactory

@Composable
fun Navigation(
    navController: NavHostController,
    application: Application
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.routes
    ) {
        val remoteDataSource = MoviesRemoteDataSource()
        val repository: Repository = RepositoryImpl(remoteDataSource)
        val getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
        val searchMoviesUseCase = SearchMoviesUseCase(repository)
        val getMovieDetailsUseCase = GetMovieDetailsUseCase(repository)
        val getMovieActorsUseCase = GetMovieActorsUseCase(repository)


        composable(Routes.MainScreen.routes) {
            val mainScreenViewModel: MainScreenViewModel =
                viewModel(factory = viewModelFactory {
                    MainScreenViewModel(
                        getPopularMoviesUseCase,
                        searchMoviesUseCase,
                        application
                    )
                })
            MainScreen(navController = navController, mainScreenViewModel)
        }
        composable("${Routes.DetailsScreen.routes}/{filmId}") {
            it.arguments?.let { arguments ->

                val detailsViewModel: DetailsViewModel =
                    viewModel(factory = viewModelFactory {
                        DetailsViewModel(
                            getMovieDetailsUseCase,
                            getMovieActorsUseCase,
                            arguments.getString("filmId")!!.toInt()
                        )
                    })
                DetailsScreen(
                    detailsViewModel,
                )
            }
        }
    }
}