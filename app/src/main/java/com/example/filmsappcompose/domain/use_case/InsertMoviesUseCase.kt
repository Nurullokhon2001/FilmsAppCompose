package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Movie

class InsertMoviesUseCase(private val repository: Repository) {
    suspend operator fun invoke(movies: List<Movie>) {
        repository.insertMovies(movies)
    }
}