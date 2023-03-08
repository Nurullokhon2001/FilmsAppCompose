package com.example.filmsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.filmsappcompose.data.remote.MoviesRemoteDataSource
import com.example.filmsappcompose.data.repository.RepositoryImpl
import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.use_case.GetPopularMoviesUseCase
import com.example.filmsappcompose.domain.use_case.SearchMoviesUseCase
import com.example.filmsappcompose.presentation.main_screen.MainScreenViewModel
import com.example.filmsappcompose.presentation.ui.navigation.Navigation
import com.example.filmsappcompose.presentation.ui.theme.FilmsAppComposeTheme
import com.example.filmsappcompose.utiils.viewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val remoteDataSource = MoviesRemoteDataSource()
        val repository: Repository = RepositoryImpl(remoteDataSource)
        val getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
        val searchMoviesUseCase = SearchMoviesUseCase(repository)

        setContent {
            FilmsAppComposeTheme {
                val mainScreenViewModel: MainScreenViewModel =
                    viewModel(factory = viewModelFactory {
                        MainScreenViewModel(this.application, getPopularMoviesUseCase,searchMoviesUseCase)
                    })
                val navController = rememberNavController()
                Navigation(navController, mainScreenViewModel)
            }
        }
    }
}