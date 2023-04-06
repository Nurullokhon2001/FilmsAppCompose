package com.example.filmsappcompose.presentation.ui.main_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.filmsappcompose.domain.model.Genre

@Composable
fun CategoriesItem(category: Genre, onclick: (Genre) -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black, CircleShape)
            .clickable { onclick.invoke(category) }
            .padding(horizontal = 15.dp, vertical = 4.dp)
    ) {
        Text(text = category.name)
    }
}