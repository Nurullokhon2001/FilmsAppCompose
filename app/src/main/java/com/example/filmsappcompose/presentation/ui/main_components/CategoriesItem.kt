package com.example.filmsappcompose.presentation.ui.main_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.filmsappcompose.domain.model.Genre

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesItem(category: Genre, onclick: (Genre) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        onClick = {
            onclick.invoke(category)
        }) {
        Text(
            text = category.name, modifier = Modifier
                .border(1.dp, Color.Black, CircleShape)
                .padding(horizontal = 15.dp, vertical = 4.dp)
        )
    }
}