package com.example.filmsappcompose.data.remote

import com.example.filmsappcompose.data.dto.toDomain
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.runOperationCatching

class MoviesRemoteDataSource {
    suspend fun getPopularMovies(): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            RetrofitClient.create().getPopularMovies().toDomain()
        }
    }
}