package com.example.filmsappcompose.domain.model

import com.example.filmsappcompose.data.dto.ActorDto
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetails(
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