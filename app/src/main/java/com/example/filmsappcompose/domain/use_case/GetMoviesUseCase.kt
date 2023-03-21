package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<Resource<List<Movie>, Throwable>> {
        return repository.getMovies()
    }
}