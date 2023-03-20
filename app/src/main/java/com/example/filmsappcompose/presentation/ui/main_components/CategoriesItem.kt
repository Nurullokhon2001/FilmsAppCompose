package com.example.filmsappcompose.presentation.ui.main_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesItem(category: String, onclick: (String) -> Unit) {
    Text(
        text = category,
        modifier = Modifier
            .border(1.dp, Color.Black, CircleShape)
            .padding(horizontal = 15.dp, vertical = 4.dp)
            .clickable { onclick.invoke(category) }
    )
}