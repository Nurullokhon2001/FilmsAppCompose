package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.model.Movie
import javax.inject.Inject

class FilterByGenresUseCase @Inject constructor() {
    operator fun invoke(movies: List<Movie>, genreId: Int): List<Movie> {
        return movies.filter { it.genres.contains(genreId) }
    }
}