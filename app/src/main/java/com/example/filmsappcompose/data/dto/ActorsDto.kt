package com.example.filmsappcompose.data.dto

import com.example.filmsappcompose.domain.model.Actor

data class ActorsDto(val cast: List<ActorDto>)

fun List<ActorDto>.toDomain(): List<Actor> {
    return this.map { it.toDomain() }
}