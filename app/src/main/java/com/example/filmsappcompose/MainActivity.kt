package com.example.filmsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.filmsappcompose.main_screen.MainScreenViewModel
import com.example.filmsappcompose.ui.navigation.Navigation
import com.example.filmsappcompose.ui.theme.FilmsAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainScreenViewModel by viewModels<MainScreenViewModel>()
        setContent {
            FilmsAppComposeTheme {
                val navController = rememberNavController()
                Navigation(navController, mainScreenViewModel)
            }
        }
    }
}