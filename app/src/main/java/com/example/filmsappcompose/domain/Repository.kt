package com.example.filmsappcompose.domain

import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.domain.model.Genre
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPopularMovies(): Flow<Resource<List<Movie>, Throwable>>
    suspend fun getMovies(): Flow<Resource<List<Movie>, Throwable>>
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun searchMovies(query: String): Flow<Resource<List<Movie>, Throwable>>
    suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetails, Throwable>>
    suspend fun getMovieActors(movieId: Int): Flow<Resource<List<Actor>,Throwable>>
    suspend fun getGenre():Flow<Resource<List<Genre>,Throwable>>
}