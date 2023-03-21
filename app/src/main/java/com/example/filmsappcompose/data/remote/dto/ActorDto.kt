package com.example.filmsappcompose.data.remote.dto

import com.example.filmsappcompose.domain.model.Actor
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

private const val BASE_URL_IMAGE = "http://image.tmdb.org/t/p/w500/"

@Serializable
data class ActorDto(
    val name: String,
    @SerializedName("profile_path") val photo: String?
)

fun ActorDto.toDomain() = Actor(name = name, photo = BASE_URL_IMAGE + photo)