package com.example.filmsappcompose.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.presentation.main_screen.components.FilmCard
import com.example.filmsappcompose.presentation.main_screen.components.SearchBar
import com.example.filmsappcompose.presentation.ui.main_components.CategoriesItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavHostController, mainScreenViewModel: MainScreenViewModel) {
    val films = mainScreenViewModel.movie.collectAsState()
    val category = remember { mutableStateOf("Popular now") }
    val templateSearchValue = remember { mutableStateOf("") }

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        mainScreenViewModel.leadingIconClicked()
        delay(1000L)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        modifier = Modifier
            .pullRefresh(state)
            .fillMaxSize()
    ) {
        if (!refreshing) {
            Column {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    modifier = Modifier.height(65.dp)
                ) {
                    SearchBar(onValueChange = {
                        templateSearchValue.value = it
                        mainScreenViewModel.onValueChange(templateSearchValue.value)
                    }, leadingIconClicked = {
                        templateSearchValue.value = ""
                        category.value = "Popular now"
                        mainScreenViewModel.leadingIconClicked()
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
                    items(mainScreenViewModel.genre.value) {
                        CategoriesItem(category = it, onclick = { genre ->
                            category.value = genre.name
                            //   mainScreenViewModel.filterByGenre(genre.id)
                        })
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    when (films.value) {
                        is MainScreenState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center)
                            )
                        }

                        is MainScreenState.Error -> {
                            Text(
                                text = (films.value as MainScreenState.Error).error.message.toString(),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxSize()
                                    .verticalScroll(
                                        rememberScrollState()
                                    )
                            )
                        }

                        is MainScreenState.Content -> {
                            GetFilmsPaging(
                                navController,
                                (films.value as MainScreenState.Content).data.collectAsLazyPagingItems()
                            )
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun GetFilmsPaging(navController: NavHostController, movies: LazyPagingItems<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = movies.itemCount,
            key = movies.itemKey(),
            contentType = movies.itemContentType(
            )
        ) { index ->
            val item = movies[index]
            if (item != null) {
                FilmCard(movie = item, navController)
            }
        }
        item {
            if (movies.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
        item {
            if (movies.loadState.append is LoadState.Error) {
                Timber.tag("error").e("Paging Error State")
            }
        }
    }
}