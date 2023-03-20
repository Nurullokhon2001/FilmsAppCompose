package com.example.filmsappcompose.data.remote

import com.example.filmsappcompose.data.dto.toDomain
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.runOperationCatching

class MoviesRemoteDataSource {
    suspend fun getPopularMovies(): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            RetrofitClient.create().getPopularMovies().toDomain()
        }
    }

    suspend fun searchMovies(query: String): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            RetrofitClient.create().searchMovies(query = query).toDomain()
        }
    }

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails, Throwable> {
        return runOperationCatching {
            RetrofitClient.create().getMovieDetails(movieId).toDomain()
        }
    }

    suspend fun getMovieActors(movieId: Int): List<Actor> {
        return RetrofitClient.create().getMovieActors(movieId).cast.toDomain()
    }
}