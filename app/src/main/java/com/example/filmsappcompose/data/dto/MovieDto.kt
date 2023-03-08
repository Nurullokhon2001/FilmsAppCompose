package com.example.filmsappcompose.data.dto

import com.example.filmsappcompose.domain.model.Movie
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("poster_path") val image: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("overview") val description: String,
    val age: String? = "0",
)


fun MovieDto.toDomain() = Movie(
    id = id,
    name = name,
    image = image,
    rating = rating.div(2),
    description = description,
    age = age ?: "0"
)
