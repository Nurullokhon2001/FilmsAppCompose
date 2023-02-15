package com.example.filmsappcompose.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.filmsappcompose.R
import com.example.filmsappcompose.main_screen.MainScreenViewModel
import com.example.filmsappcompose.utiils.convertLongToTime

@Composable
fun DetailsScreen(
    mainScreenViewModel: MainScreenViewModel,
    filmId: Int
) {
    Column {
        val film = mainScreenViewModel.films.value[filmId]
        Text(text = film.name, modifier = Modifier.padding(0.dp, 10.dp))
        Text(
            text = film.date_publication.convertLongToTime(),
            modifier = Modifier.padding(0.dp, 10.dp)
        )
        Text(text = film.description, modifier = Modifier.padding(0.dp, 10.dp))
        Row {
            for (i in 1..5) {
                if (film.rating != null && film.rating >= i) {
                    Image(
                        painter = painterResource(id = R.drawable.start_black),
                        modifier = Modifier.size(15.dp),
                        contentDescription = null
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.star_white),
                        modifier = Modifier.size(15.dp),
                        contentDescription = null
                    )
                }
            }
        }
    }
}