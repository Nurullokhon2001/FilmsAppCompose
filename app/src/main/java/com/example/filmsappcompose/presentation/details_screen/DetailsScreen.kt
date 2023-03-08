package com.example.filmsappcompose.presentation.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmsappcompose.R
import com.example.filmsappcompose.data.dto.ActorDto
import com.example.filmsappcompose.presentation.main_screen.MainScreenViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    mainScreenViewModel: MainScreenViewModel,
    filmId: Int
) {
//    val state = mainScreenViewModel.films.collectAsState()
//    val film = (state.value as Resource.Success).data[filmId]
//    val configuration = LocalConfiguration.current
//
//    BottomSheetScaffold(
//        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 16.dp),
//        sheetPeekHeight = configuration.screenHeightDp.dp - (configuration.screenHeightDp.dp / 3),
//        sheetContent = {
//            Column(
//                verticalArrangement = Arrangement.Bottom,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 30.dp, start = 20.dp, end = 20.dp),
//                ) {
//                    CategoriesItem(category = film.category, onclick = {})
//                    Text(
//                        text = film.date_publication.convertLongToTime(),
//                        Modifier
//                            .padding(start = 8.dp)
//                            .align(Alignment.CenterVertically),
//                        fontSize = 12.sp,
//                        color = Color.Black
//                    )
//                }
//                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
//                ) {
//                    Text(
//                        text = film.name,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp,
//                        modifier = Modifier
//                            .padding(bottom = 8.dp, top = 16.dp)
//                            .align(Alignment.Bottom),
//                    )
//                    AgeBar(age = film.age, 70f)
//                }
//                Box(modifier = Modifier.padding(bottom = 20.dp, start = 20.dp)) {
//                    film.rating?.let { CustomRatingView(rating = it) }
//                }
//                Text(
//                    text = film.description,
//                    fontSize = 20.sp,
//                    modifier = Modifier.padding(horizontal = 20.dp)
//                )
//                Text(
//                    text = "Актеры",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    modifier = Modifier
//                        .padding(bottom = 8.dp, top = 16.dp, start = 20.dp)
//                        .align(Alignment.Start),
//                )
//                LazyRow(
//                    contentPadding = PaddingValues(
//                        horizontal = 20.dp, vertical = 18.dp
//                    ),
//                    horizontalArrangement = Arrangement.spacedBy(16.dp),
//                ) {
//                    items(film.actors) {
//                        ActorItem(it)
//                    }
//                }
//            }
//        }) {
//        Image(
//            contentScale = ContentScale.FillWidth,
//            modifier = Modifier
//                .fillMaxWidth(),
//            painter = painterResource(R.drawable.test_image2),
//            contentDescription = null,
//        )
//    }
}

@Composable
fun ActorItem(actor: ActorDto) {
    Column() {
        Image(
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.actor),
            contentDescription = null,
        )
        Text(text = actor.name, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}