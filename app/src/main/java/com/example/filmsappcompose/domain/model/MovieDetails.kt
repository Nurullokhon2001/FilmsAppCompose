package com.example.filmsappcompose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val name: String,
    val image: String,
    val datePublication: String,
    val rating: Float?,
    val description: String,
    val age: String = "0",
    val genre: Genre,
    val actors: List<Actor>
)