package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow

class GetMovieActorsUseCase(private val repository: Repository) {
    suspend operator fun invoke(movieId: Int): List<Actor> {
        return repository.getMovieActors(movieId)
    }
}