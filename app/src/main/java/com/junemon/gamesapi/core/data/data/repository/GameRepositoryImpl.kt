package com.junemon.gamesapi.core.data.data.repository

import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.di.module.DefaultDispatcher
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.model.ConsumeResult
import com.junemon.model.DataHelper
import com.junemon.model.GenericPair
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import com.junemon.model.games.GameSearch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
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
    private val cacheDataSource: GameCacheDataSource
) : GameRepository {


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

    override fun getSearchGames(query: String): Flow<ConsumeResult<GameSearch>>  = flow {
        when (val response = remoteDataSource.getSearchGames(query)) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }.debounce(1000).onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
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

    override fun saveGames(data: GameData) {
        cacheDataSource.saveGames(data)
    }

    override fun getCachedGames(): GameData {
        return cacheDataSource.getGames()
    }
}