package com.junemon.gamesapi.core.data.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GamePaginationRemoteDataSource
import com.junemon.gamesapi.core.di.module.DefaultDispatcher
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.model.CachedDataHelper
import com.junemon.model.ConsumeCacheResult
import com.junemon.model.ConsumeResult
import com.junemon.model.DataHelper
import com.junemon.model.GenericPair
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import com.junemon.model.games.GameSearch
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

@ExperimentalCoroutinesApi
@FlowPreview
class GameRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: GameRemoteDataSource,
    private val pagingRemoteDataSource: GamePaginationRemoteDataSource,
    private val cacheDataSource: GameCacheDataSource
) : GameRepository {

    override fun getCachedListGames(): Flow<ConsumeCacheResult<GameEntity>> {

        return flow {
            cacheDataSource.getGames().combine(remoteDataSource.getFlowListGames()) { a, b ->
                GenericPair(a, b)
            }.collect {
                when (val remoteResponse = it.data2) {
                    is CachedDataHelper.RemoteSourceValue -> {
                        cacheDataSource.saveGames(remoteResponse.data)
                        emit(ConsumeCacheResult.ConsumeData(remoteResponse.data.map { remoteResult ->
                            GameEntity(
                                gameId = remoteResult.id,
                                gameName = remoteResult.name,
                                gameGenre = remoteResult.genres?.get(0)?.name,
                                gameImage = remoteResult.backgroundImage
                            )
                        }))
                    }
                    is CachedDataHelper.RemoteSourceError -> {
                        emit(ConsumeCacheResult.ErrorHappen(remoteResponse.exception))
                    }
                    is CachedDataHelper.Loading -> {
                        emit(ConsumeCacheResult.Loading(it.data1))
                    }
                    is CachedDataHelper.Complete -> {
                        emit(ConsumeCacheResult.Complete)
                    }
                }
            }
        }
    }

    override fun getListGames() = flow {
        when (val response = remoteDataSource.getListGames()) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
        .flowOn(defaultDispatcher).conflate()

    override fun getListGamesByGenres(): Flow<ConsumeResult<GameGenre>> = flow {
        when (val response = remoteDataSource.getListGamesByGenres()) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
        .flowOn(defaultDispatcher).conflate()

    override fun getGenreAndGames(): Flow<GenericPair<ConsumeResult<GameData>, ConsumeResult<GameGenre>>> =
        combine(
            flow = getListGames(),
            flow2 = getListGamesByGenres()
        ) { a, b -> GenericPair(a, b) }

    override fun getSearchGames(query: String): Flow<ConsumeResult<GameSearch>> = flow {
        when (val response = remoteDataSource.getSearchGames(query)) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data))
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
                emit(ConsumeResult.ConsumeSingleData(response.data))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
        .flowOn(defaultDispatcher).conflate()

    override fun getPagingListGames(): Flow<PagingData<GameData>> {
        val NETWORK_PAGE_SIZE = 20
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pagingRemoteDataSource }
        ).flow
    }

    override fun registerPreferenceListener() {
        cacheDataSource.registerPreferenceListener()
    }

    override fun unregisterPreferenceListener() {
        cacheDataSource.unregisterPreferenceListener()
    }

    override fun saveStringInSharedPreference(key: String, value: String?) {
        cacheDataSource.saveStringInSharedPreference(key, value)
    }

    override fun getStringInSharedPreference(key: String): Flow<String?> {
        return cacheDataSource.getStringInSharedPreference(key)
    }

    override fun saveIntInSharedPreference(key: String, value: Int?) {
        cacheDataSource.saveIntInSharedPreference(key, value)
    }

    override fun getIntInSharedPreference(key: String): Flow<Int?> {
        return cacheDataSource.getIntInSharedPreference(key)
    }

    override fun saveBooleanInSharedPreference(key: String, value: Boolean) {
        cacheDataSource.saveBooleanInSharedPreference(key, value)
    }

    override fun getBooleanInSharedPreference(key: String): Flow<Boolean> {
        return cacheDataSource.getBooleanInSharedPreference(key)
    }

    override fun deleteSharedPreference(key: String) {
        cacheDataSource.deleteAllSharedPrefrence()
    }

    override fun deleteAllSharedPrefrence() {
        cacheDataSource.deleteAllSharedPrefrence()
    }
}