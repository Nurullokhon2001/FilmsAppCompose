package com.example.filmsappcompose.data.repository

import androidx.paging.PagingData
import com.example.filmsappcompose.data.local.db.MoviesLocalDataSource
import com.example.filmsappcompose.data.remote.network.MoviesRemoteDataSource
import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Actor
import com.example.filmsappcompose.domain.model.Genre
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.domain.model.MovieDetails
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: MoviesRemoteDataSource,
    private val local: MoviesLocalDataSource
) : Repository {

    override suspend fun getPopularMoviesPaging(query: String): Flow<Resource<Flow<PagingData<Movie>>, Throwable>> {
        return flow {
            emit(local.getPopularMoviesPaging(query))
        }
    }

    override suspend fun getPopularMoviesNetwork(): Flow<Resource<List<Movie>, Throwable>> {
        return flow {
            emit(remote.getPopularMoviesNetwork())
        }
    }

    override suspend fun getPopularMoviesLocal(): Flow<Resource<List<Movie>, Throwable>> {
        return flow {
            emit(local.getPopularMoviesLocal())
        }
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        local.insertMovies(movies)
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

    override suspend fun getMovieActors(movieId: Int): Flow<Resource<List<Actor>, Throwable>> {
        return flow {
            emit(remote.getMovieActors(movieId))
        }
    }

    override suspend fun getGenre(): Flow<Resource<List<Genre>, Throwable>> {
        return flow {
            emit(remote.getGenre())
        }
    }
}