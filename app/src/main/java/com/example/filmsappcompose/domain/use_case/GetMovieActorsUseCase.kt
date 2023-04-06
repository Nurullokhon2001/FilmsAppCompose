package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieActorsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(movieId: Int): Flow<Resource<List<Actor>, Throwable>> {
        return repository.getMovieActors(movieId)
    }
}