package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.di.module.DefaultDispatcher
import com.junemon.gamesapi.core.network.ApiInterface
import com.junemon.gamesapi.core.network.BaseSources
import com.junemon.gamesapi.util.CacheOnSuccess
import com.junemon.model.CachedDataHelper
import com.junemon.model.DataHelper
import com.junemon.model.Results
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import com.junemon.model.games.GameSearch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseSources(), GameRemoteDataSource {

    override fun getFlowListGames(): Flow<CachedDataHelper<List<GameData>>> {
        return flow {
            when (val responses = oneShotCalls { api.getListGames() }) {
                is Results.Success -> {
                    emit(CachedDataHelper.RemoteSourceValue(responses.data.data))
                }
                is Results.Error -> {
                    emit(CachedDataHelper.RemoteSourceError(responses.exception))
                }
            }
        }.flowOn(defaultDispatcher).conflate().onStart { emit(CachedDataHelper.Loading) }
            .onCompletion { emit(CachedDataHelper.Complete) }
    }

    override suspend fun getListGames(): DataHelper<List<GameData>> =
        withContext(defaultDispatcher) {
            when (val responses = oneShotCalls { api.getListGames() }) {
                is Results.Success -> {
                    val resultMemoryCache = CacheOnSuccess(onErrorFallback = { listOf<GameData>() }) {
                        responses.data.data
                    }
                    DataHelper.RemoteSourceValue(resultMemoryCache.getOrAwait())
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }

    override suspend fun getListGamesByGenres(): DataHelper<List<GameGenre>> =
        withContext(defaultDispatcher) {
            when (val responses = oneShotCalls { api.getListGamesByGenres() }) {
                is Results.Success -> {
                    //trying to use memory cache
                    val resultMemoryCache = CacheOnSuccess(onErrorFallback = { listOf<GameGenre>() }) {
                        responses.data.data
                    }
                    DataHelper.RemoteSourceValue(responses.data.data)
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }

    override suspend fun getDetailGames(gameId: Int): DataHelper<GameDetail> =
        withContext(defaultDispatcher) {
            when (val responses = oneShotCalls { api.getDetailGames(gameId) }) {
                is Results.Success -> {
                    DataHelper.RemoteSourceValue(responses.data)
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }

    override suspend fun getSearchGames(query: String): DataHelper<List<GameSearch>> =
        withContext(defaultDispatcher) {
            when (val responses = oneShotCalls { api.getSearchGames(query) }) {
                is Results.Success -> {
                    DataHelper.RemoteSourceValue(responses.data.data)
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }
}