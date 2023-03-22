package com.example.filmsappcompose.domain.use_case

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Genre
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Genre> {
        return repository.getGenre()
    }
}