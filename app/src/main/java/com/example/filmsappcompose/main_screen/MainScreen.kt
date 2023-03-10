package com.example.filmsappcompose.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.filmsappcompose.main_screen.components.FilmCard
import com.example.filmsappcompose.main_screen.components.SearchBar
import com.example.filmsappcompose.main_screen.domain.Film
import com.example.filmsappcompose.ui.main_components.CategoriesItem
import com.example.filmsappcompose.utiils.Resource

@Composable
fun MainScreen(navController: NavHostController, mainScreenViewModel: MainScreenViewModel) {
    val films = mainScreenViewModel.films.collectAsState()
    val category = remember { mutableStateOf("Популярное сейчас") }
    val templateSearchValue = remember { mutableStateOf("") }
    Column() {
        TopAppBar(
            backgroundColor = Color.Transparent, elevation = 0.dp, modifier = Modifier.height(65.dp)
        ) {
            SearchBar(onValueChange = {
                templateSearchValue.value = it
                mainScreenViewModel.onValueChange(templateSearchValue.value, category.value)
            }, leadingIconClicked = {
                mainScreenViewModel.leadingIconClicked()
                templateSearchValue.value = ""
                category.value = "Популярное сейчас"
            })
        }
        Text(
            text = category.value,
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 20.dp, vertical = 18.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(mainScreenViewModel.categories.value) {
                CategoriesItem(category = it, onclick = {
                    category.value = it
                    mainScreenViewModel.onValueChange(templateSearchValue.value, category.value)
                })
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when (films.value) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }
                is Resource.Error -> {
                    Text(
                        text = (films.value as Resource.Error).throwable.message.toString(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Resource.Success -> {
                    GetFilms(navController, (films.value as Resource.Success).data)
                }
            }
        }
    }
}

@Composable
fun GetFilms(navController: NavHostController, films: List<Film>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
    ) {
        items(films) { movie ->
            FilmCard(film = movie, navController)
        }
    }
}