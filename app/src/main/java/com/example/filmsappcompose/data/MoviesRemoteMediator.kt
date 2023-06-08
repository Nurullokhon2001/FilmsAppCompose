package com.example.filmsappcompose.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.entity.MovieEntity
import com.example.filmsappcompose.data.remote.dto.toEntity
import com.example.filmsappcompose.data.remote.network.ApiInterface
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator @Inject constructor(
    private val dbDao: MoviesDao,
    private val apiService: ApiInterface,
    private val query: String,
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        dbDao.getMovies().size / 10
                    }
                }
            }

            val movies = if (query.isEmpty()) {

                apiService.getPopularMovies(page = loadKey).results
            } else {

                apiService.searchMovies(query = query, page = loadKey).results
            }

            if (loadType == LoadType.REFRESH) {
                dbDao.deleteMovies()
            }
            val movieEntity = movies.map { it.toEntity() }
            dbDao.insertMovies(movieEntity)

            MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}