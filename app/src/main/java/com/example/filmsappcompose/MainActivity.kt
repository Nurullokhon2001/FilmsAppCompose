package com.example.filmsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmsappcompose.presentation.details_screen.DetailsScreen
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModel
import com.example.filmsappcompose.presentation.main_screen.MainScreen
import com.example.filmsappcompose.presentation.main_screen.MainScreenViewModel
import com.example.filmsappcompose.presentation.main_screen.MainViewModelFactory
import com.example.filmsappcompose.presentation.ui.navigation.Navigation
import com.example.filmsappcompose.presentation.ui.navigation.Routes
import com.example.filmsappcompose.presentation.ui.theme.FilmsAppComposeTheme
import com.example.filmsappcompose.utiils.viewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var lainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmsAppComposeTheme {
                (applicationContext as MainApp).appComponent.inject(this)
                val mainScreenViewModel: MainScreenViewModel = viewModel(
                    factory = lainViewModelFactory,
                )

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.MainScreen.routes
                ) {

                    composable(Routes.MainScreen.routes) {
                        MainScreen(navController = navController, mainScreenViewModel)
                    }
                    composable("${Routes.DetailsScreen.routes}/{filmId}") {
                        it.arguments?.let { arguments ->

//                            val detailsViewModel: DetailsViewModel =
//                                viewModel(factory = viewModelFactory {
//                                    DetailsViewModel(
//                                        getMovieDetailsUseCase,
//                                        getMovieActorsUseCase,
//                                        arguments.getString("filmId")!!.toInt()
//                                    )
//                                })
//                            DetailsScreen(
//                                detailsViewModel,
//                            )
                        }
                    }
                }
            }
        }
    }
}