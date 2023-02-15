package com.example.filmsappcompose.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.filmsappcompose.R
import com.example.filmsappcompose.main_screen.domain.Film
import com.example.filmsappcompose.ui.navigation.Routes
import com.example.filmsappcompose.utiils.convertLongToTime

@Composable
fun FilmCard(film: Film, navController: NavHostController) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable {
                navController.navigate(route = "${Routes.DetailsScreen.routes}/" + film.id)
            }
    ) {
        Card(
            modifier = Modifier.background(Color.Transparent),
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Column {
                Image(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    painter = painterResource(R.drawable.test_image),
                    contentDescription = null,
                )
                Text(text = film.name, color = Color.Black)
                Text(text = film.date_publication.convertLongToTime(), color = Color.Black)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FilmCardPreview() {
    val film = Film(
        5,
        "Film 5",
        "https://ru.wikipedia.org/wiki/Аватар:_Путь_воды#/media/Файл:Аватар_Путь_воды_постер.jpg",
        1675209600,
        5,
        "Description 5",
        5
    )
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .background(Color.Transparent),
    ) {
        Card(
            modifier = Modifier.background(Color.Transparent),
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Column {
                Image(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    painter = painterResource(R.drawable.test_image),
                    contentDescription = null,
                )
                Text(text = film.name, color = Color.Black)
                Text(text = film.date_publication.convertLongToTime(), color = Color.Black)
            }
        }
    }
}