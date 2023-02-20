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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.filmsappcompose.R
import com.example.filmsappcompose.main_screen.domain.Film
import com.example.filmsappcompose.ui.main_components.AgeBar
import com.example.filmsappcompose.ui.main_components.CustomRatingBar
import com.example.filmsappcompose.ui.navigation.Routes

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
                Text(
                    text = film.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = film.description,
                    color = Color.Black,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp, end = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomRatingBar(film.rating)
                    AgeBar(film.age)
                }
            }
        }
    }
}