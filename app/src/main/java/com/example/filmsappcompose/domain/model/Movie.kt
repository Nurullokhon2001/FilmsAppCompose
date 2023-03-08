package com.example.filmsappcompose.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val image: String,
    val rating: Float = 0f,
    val description: String,
    val age: String = "0",
)
