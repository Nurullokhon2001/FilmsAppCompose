package com.example.filmsappcompose.ui.main_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.filmsappcompose.R

@Composable
fun CustomRatingBar(rating: Byte?) {
    Row {
        for (i in 1..5) {
            if (rating != null && rating >= i) {
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