package com.example.filmsappcompose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val results: List<MovieDto>
)

fun Movies.toDomain() = results.map { it.toDomain() }