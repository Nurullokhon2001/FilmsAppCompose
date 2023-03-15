package com.example.filmsappcompose.presentation.details_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.filmsappcompose.R
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.presentation.ui.main_components.AgeBar
import com.example.filmsappcompose.presentation.ui.main_components.CategoriesItem
import com.example.filmsappcompose.presentation.ui.main_components.CustomRatingView

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    detailsScreenViewModel: DetailsViewModel,
) {

    val state = detailsScreenViewModel.movieDetails.collectAsState()
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier.fillMaxSize()) {
        when (state.value) {
            is DetailsScreenState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
            is DetailsScreenState.Error -> {
                Text(
                    text = (state.value as DetailsScreenState.Error).error.message.toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is DetailsScreenState.Content -> {
                BottomSheetScaffold(
                    sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 16.dp),
                    sheetPeekHeight = configuration.screenHeightDp.dp - (configuration.screenHeightDp.dp / 3),
                    sheetContent = {
                        when (state.value) {
                            is DetailsScreenState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                            is DetailsScreenState.Error -> {
                                Text(
                                    text = (state.value as DetailsScreenState.Error).error.message.toString(),
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                            is DetailsScreenState.Content -> {
                                val film = (state.value as DetailsScreenState.Content).data
                                Column(
                                    verticalArrangement = Arrangement.Bottom,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                                    ) {
                                        CategoriesItem(category = film.category, onclick = {})
                                        Text(
                                            text = film.datePublication,
                                            Modifier
                                                .padding(start = 8.dp)
                                                .align(Alignment.CenterVertically),
                                            fontSize = 12.sp,
                                            color = Color.Black
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
                                    ) {
                                        Text(
                                            text = film.name,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            modifier = Modifier
                                                .padding(bottom = 8.dp, top = 16.dp)
                                                .align(Alignment.Bottom),
                                        )
                                        AgeBar(age = film.age, 70f)
                                    }
                                    Box(
                                        modifier = Modifier.padding(
                                            bottom = 20.dp,
                                            start = 20.dp
                                        )
                                    ) {
                                        film.rating?.let { CustomRatingView(rating = it) }
                                    }
                                    Text(
                                        text = film.description,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(horizontal = 20.dp)
                                    )
                                    Text(
                                        text = "Актеры",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(bottom = 8.dp, top = 16.dp, start = 20.dp)
                                            .align(Alignment.Start),
                                    )
                                    LazyRow(
                                        contentPadding = PaddingValues(
                                            horizontal = 20.dp, vertical = 18.dp
                                        ),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    ) {
                                        items(film.actors) {
                                            ActorItem(it)
                                        }
                                    }
                                }
                            }
                        }
                    }) {
                    AsyncImage(
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth(),
                        model = (state.value as DetailsScreenState.Content).data.image,
                        placeholder = painterResource(R.drawable.test_image),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Composable
fun ActorItem(actor: Actor) {
    Column() {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
            model = actor.photo,
            placeholder = painterResource(R.drawable.test_image),
            contentDescription = null,
        )
        Text(text = actor.name, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}