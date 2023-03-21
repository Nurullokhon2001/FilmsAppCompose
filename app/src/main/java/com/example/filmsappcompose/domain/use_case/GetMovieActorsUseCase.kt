package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Actor
import javax.inject.Inject

class GetMovieActorsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(movieId: Int): List<Actor> {
        return repository.getMovieActors(movieId)
    }
}