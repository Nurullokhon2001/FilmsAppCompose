package com.example.filmsappcompose.data.repository

import com.example.filmsappcompose.data.remote.MoviesRemoteDataSource
import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val remote: MoviesRemoteDataSource
) : Repository {
    override suspend fun getPopularMovies(): Flow<Resource<List<Movie>, Throwable>> {
        return flow {
            emit(remote.getPopularMovies())
        }
    }

    override suspend fun searchMovies(query: String): Flow<Resource<List<Movie>, Throwable>> {
        return flow {
            emit(remote.searchMovies(query))
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetails, Throwable>> {
        return flow {
            emit(remote.getMovieDetails(movieId))
        }
    }

    override suspend fun getMovieActors(movieId: Int): List<Actor> {
        return remote.getMovieActors(movieId)
    }
}