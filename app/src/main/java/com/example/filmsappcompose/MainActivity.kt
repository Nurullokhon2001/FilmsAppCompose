package com.example.filmsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.filmsappcompose.presentation.details_screen.DetailsViewModelAssistedFactory
import com.example.filmsappcompose.presentation.main_screen.MainViewModelFactory
import com.example.filmsappcompose.presentation.ui.navigation.Navigation
import com.example.filmsappcompose.presentation.ui.theme.FilmsAppComposeTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var detailsViewModelAssistedFactory: DetailsViewModelAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmsAppComposeTheme {
                (applicationContext as MainApp).appComponent.inject(this)
                val navController = rememberNavController()
                Navigation(
                    navController, mainViewModelFactory, detailsViewModelAssistedFactory
                )
            }
        }
    }
}