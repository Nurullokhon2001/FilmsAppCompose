package com.example.filmsappcompose.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailsDto(
    val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("poster_path") val image: String,
    val date_publication: Long,
    val rating: Float?,
    @SerializedName("overview") val description: String,
    val age: String = "0",
    val category: String,
    val actors: List<ActorDto>
)