package com.example.filmsappcompose.data.remote.dto

import com.example.filmsappcompose.domain.model.Genre

data class GenreDto(val id: Int, val name: String)

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}