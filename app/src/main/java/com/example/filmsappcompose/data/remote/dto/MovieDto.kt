package com.example.filmsappcompose.data.remote.dto

import com.example.filmsappcompose.domain.model.Movie
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

private const val BASE_URL_IMAGE = "http://image.tmdb.org/t/p/w500/"

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
    image = BASE_URL_IMAGE + image,
    rating = rating.div(2),
    description = description,
    age = age ?: "0"
)
