package com.example.filmsappcompose.presentation.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.filmsappcompose.R
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.presentation.ui.main_components.AgeBar
import com.example.filmsappcompose.presentation.ui.main_components.CustomRatingView
import com.example.filmsappcompose.presentation.ui.navigation.Routes

@Composable
fun FilmCard(movie: Movie, navController: NavHostController) {
    Box(modifier = Modifier
        .padding(20.dp)
        .fillMaxSize()
        .background(Color.Transparent)
        .clickable {
            navController.navigate(route = "${Routes.DetailsScreen.routes}/${movie.id}")
        }) {
        Card(
            modifier = Modifier.background(Color.Transparent),
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Column {
                SubcomposeAsyncImage(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    model = movie.image,
                    loading = {
                        CircularProgressIndicator(Modifier.fillMaxSize())
                    },
                    error = {
                        Image(
                            painter = painterResource(id = R.drawable.test_image),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = ""
                        )
                    },
                    contentDescription = null,
                )
                Text(
                    text = movie.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.description,
                    color = Color.Black,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp, end = 10.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomRatingView(modifier = Modifier.fillMaxHeight(), movie.rating, 3.dp)
                    AgeBar(movie.age)
                }
            }
        }
    }
}