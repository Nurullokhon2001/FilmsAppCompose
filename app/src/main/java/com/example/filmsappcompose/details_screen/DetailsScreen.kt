package com.example.filmsappcompose.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filmsappcompose.main_screen.MainScreenViewModel
import com.example.filmsappcompose.ui.main_components.CustomRatingView
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.convertLongToTime

@Composable
fun DetailsScreen(
    mainScreenViewModel: MainScreenViewModel,
    filmId: Int
) {
    val state = mainScreenViewModel.films.collectAsState()
    val film = (state.value as Resource.Success).data[filmId]
    Column {
        Text(text = film.name, modifier = Modifier.padding(0.dp, 10.dp))
        Text(
            text = film.date_publication.convertLongToTime(),
            modifier = Modifier.padding(0.dp, 10.dp)
        )
        Text(text = film.description, modifier = Modifier.padding(0.dp, 10.dp))
        CustomRatingView(modifier = Modifier, film.rating ?: 0f, 3.dp)
    }
}