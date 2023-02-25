package com.example.filmsappcompose.main_screen.domain


data class Film(
    val id: Int,
    val name: String,
    val image: String,
    val date_publication: Long,
    val rating: Float?,
    val description: String,
    val age: String,
    val category: Int
)
