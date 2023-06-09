package com.example.filmsappcompose.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.dao.RemoteKeysDao
import com.example.filmsappcompose.data.local.entity.MovieEntity
import com.example.filmsappcompose.data.local.entity.RemoteKeysEntity
import com.example.filmsappcompose.data.remote.dto.toEntity
import com.example.filmsappcompose.data.remote.network.ApiInterface
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator @Inject constructor(
    private val dbDao: MoviesDao,
    private val remoteKeysDao: RemoteKeysDao,
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
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            val movies = if (query.isEmpty()) {
                apiService.getPopularMovies(page = loadKey).results
            } else {
                apiService.searchMovies(query = query, page = loadKey).results
            }

            if (loadType == LoadType.REFRESH) {
                dbDao.deleteMovies()
                remoteKeysDao.clearRemoteKeys()
            }
            val movieEntity = movies.map { it.toEntity() }
            dbDao.insertMovies(movieEntity)

            val prevKey = if (loadKey == 1) null else loadKey - 1
            val nextKey = if (endOfPaginationReached) null else loadKey + 1
            val keys = movieEntity.map {
                RemoteKeysEntity(repoId = it.id.toLong(), prevKey = prevKey, nextKey = nextKey)
            }
            remoteKeysDao.insertRemoteKeys(keys)

            MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                remoteKeysDao.remoteKeysRepoId(repo.id.toLong())

            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                remoteKeysDao.remoteKeysRepoId(repo.id.toLong())
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                remoteKeysDao.remoteKeysRepoId(repoId.toLong())
            }
        }
    }
}