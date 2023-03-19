package com.example.filmsappcompose.data.local.db

import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.entity.toData
import com.example.filmsappcompose.data.local.entity.toDomain
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.runOperationCatching

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {
    suspend fun getMovies(): Resource<List<Movie>, Throwable> {
        return runOperationCatching {
            moviesDao.getMovies().map { it.toDomain() }
        }
    }

    suspend fun insertMovies(movies : List<Movie>) {
        moviesDao.insertMovies(movies.map { it.toData() })
    }
}