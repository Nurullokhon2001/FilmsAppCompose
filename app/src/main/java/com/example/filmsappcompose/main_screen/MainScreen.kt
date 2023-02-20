package com.example.filmsappcompose.main_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.filmsappcompose.main_screen.components.FilmCard
import com.example.filmsappcompose.main_screen.components.SearchBar
import com.example.filmsappcompose.ui.main_components.CategoriesItem

@Composable
fun MainScreen(navController: NavHostController, mainScreenViewModel: MainScreenViewModel) {
    val films = remember { mutableStateOf(mainScreenViewModel.films.value) }
    Column() {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            SearchBar()
        }
        Text(
            text = "Популярное сейчас",
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 18.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(mainScreenViewModel.categories.value) {
                CategoriesItem(category = it, onclick = { category ->
                    Log.e("MainScreen", "MainScreen: $category")
                })
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(films.value) { movie ->
                FilmCard(film = movie, navController)
            }
        }
    }
}