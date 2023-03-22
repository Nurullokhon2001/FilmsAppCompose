package com.example.filmsappcompose.data.remote.network

import com.example.filmsappcompose.data.remote.dto.toDomain
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.domain.model.Genre
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.runOperationCatching
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun getPopularMovies(): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            apiInterface.getPopularMovies().toDomain()
        }
    }

    suspend fun searchMovies(query: String): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            apiInterface.searchMovies(query = query).toDomain()
        }
    }

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails, Throwable> {
        return runOperationCatching {
            apiInterface.getMovieDetails(movieId).toDomain()
        }
    }

    suspend fun getMovieActors(movieId: Int): List<Actor> {
        return apiInterface.getMovieActors(movieId).cast.toDomain()
    }

    suspend fun getGenre(): List<Genre> {
        return apiInterface.getGenre().toDomain()
    }
}