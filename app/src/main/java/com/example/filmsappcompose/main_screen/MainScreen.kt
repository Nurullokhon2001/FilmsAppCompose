package com.example.filmsappcompose.main_screen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.filmsappcompose.main_screen.components.FilmCard

@Composable
fun MainScreen(navController: NavHostController, mainScreenViewModel: MainScreenViewModel) {
    val films = remember { mutableStateOf(mainScreenViewModel.films.value) }
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(films.value) { movie ->
            FilmCard(film = movie,navController)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainScreenPreview() {

}