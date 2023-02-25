package com.example.filmsappcompose.ui.main_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AgeBar(age: String, radius: Float = 30f) {
    Text(
        text = age,
        fontSize = 8.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(5.dp)
            .drawBehind {
                drawCircle(
                    Color.Black,
                    radius = radius,
                    style = Stroke(
                        width = 2f
                    )
                )
            }
    )
}