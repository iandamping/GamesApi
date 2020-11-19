package com.junemon.gamesapi.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GamePaginationRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.domain.model.CachedDataHelper
import com.junemon.gamesapi.core.domain.model.ConsumeCacheResult
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.DataHelper
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameSearch
import com.junemon.gamesapi.core.domain.model.GenericPair
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.util.mapEntitiesToDomain
import com.junemon.gamesapi.core.util.mapRemoteGameDataToDomain
import com.junemon.gamesapi.core.util.mapRemoteGenresDataToDomain
import com.junemon.gamesapi.core.util.mapRemoteSearchDataToDomain
import com.junemon.gamesapi.core.util.mapSingleRemoteDetailGameDataToDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
private const val NETWORK_PAGE_SIZE = 20

@ExperimentalCoroutinesApi
@FlowPreview
class GameRepositoryImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: GameRemoteDataSource,
    private val pagingRemoteDataSource: GamePaginationRemoteDataSource,
    private val cacheDataSource: GameCacheDataSource
) : GameRepository {

    override fun getCachedListGames(): Flow<ConsumeCacheResult<Game>> {

        return flow {
            cacheDataSource.getGames().combine(remoteDataSource.getFlowListGames()) { a, b ->
                GenericPair(a, b)
            }.collect {
                when (val remoteResponse = it.data2) {
                    is CachedDataHelper.RemoteSourceValue -> {
                        cacheDataSource.saveGames(remoteResponse.data)
                        emit(ConsumeCacheResult.ConsumeData(remoteResponse.data.mapRemoteGameDataToDomain()))
                    }
                    is CachedDataHelper.RemoteSourceError -> {
                        emit(ConsumeCacheResult.ErrorHappen(remoteResponse.exception))
                    }
                    is CachedDataHelper.Loading -> {
                        emit(ConsumeCacheResult.Loading(it.data1.mapEntitiesToDomain()))
                    }
                    is CachedDataHelper.Complete -> {
                        emit(ConsumeCacheResult.Complete)
                    }
                }
            }
        }
    }

    override fun getListGamesByGenres(): Flow<ConsumeResult<GameGenre>> = flow {
        when (val response = remoteDataSource.getListGamesByGenres()) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data.mapRemoteGenresDataToDomain()))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
        .flowOn(defaultDispatcher).conflate()

    override fun getSearchGames(query: String): Flow<ConsumeResult<GameSearch>> = flow {
        when (val response = remoteDataSource.getSearchGames(query)) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data.mapRemoteSearchDataToDomain()))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
        .flowOn(defaultDispatcher).conflate()

    override fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>> = flow {
        when (val response = remoteDataSource.getDetailGames(gameId)) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeSingleData(response.data.mapSingleRemoteDetailGameDataToDomain()))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
        .flowOn(defaultDispatcher).conflate()

    override fun getPagingListGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pagingRemoteDataSource }
        ).flow
    }
}