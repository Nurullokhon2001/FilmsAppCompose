package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val repository: Repository) {
    suspend operator fun invoke(movieId: Int): Flow<Resource<MovieDetails, Throwable>> {
        return repository.getMovieDetails(movieId)
    }
}