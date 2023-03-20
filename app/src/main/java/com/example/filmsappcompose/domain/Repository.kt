package com.example.filmsappcompose.domain

import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPopularMovies(): Flow<Resource<List<Movie>, Throwable>>
    suspend fun searchMovies(query: String): Flow<Resource<List<Movie>, Throwable>>
    suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetails, Throwable>>
    suspend fun getMovieActors(movieId: Int): List<Actor>
}