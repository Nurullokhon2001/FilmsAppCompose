package com.example.filmsappcompose.domain.use_case

import androidx.paging.PagingData
import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.utiils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesPaging @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(query : String): Flow<Resource<Flow<PagingData<Movie>>, Throwable>> {
        return repository.getPopularMoviesPaging(query)
    }
}