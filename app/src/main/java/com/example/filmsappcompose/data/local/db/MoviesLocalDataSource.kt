package com.example.filmsappcompose.data.local.db

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.filmsappcompose.data.MoviesRemoteMediator
import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.entity.MovieEntity
import com.example.filmsappcompose.data.local.entity.toData
import com.example.filmsappcompose.data.local.entity.toDomain
import com.example.filmsappcompose.data.remote.network.ApiInterface
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.runOperationCatching
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao,
    private val apiInterface: ApiInterface,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPopularMoviesPaging(query: String): Resource<Flow<PagingData<MovieEntity>>, Throwable> {
        val pagingSourceFactory = { moviesDao.getMoviesPaging() }
        val paging = Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MoviesRemoteMediator(
                query = query,
                dbDao = moviesDao,
                apiService = apiInterface
            ),
        ).flow
        return runOperationCatching { paging }
    }

    suspend fun getPopularMoviesLocal(): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            moviesDao.getMovies().map { it.toDomain() }
        }
    }

    suspend fun insertMovies(movies: List<Movie>) {
        moviesDao.insertMovies(movies.map { it.toData() })
    }
}