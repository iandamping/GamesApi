package com.junemon.gamesapi.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.junemon.gamesapi.core.data.NetworkBoundResource
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GamePaginationRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.DataHelper
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameSearch
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.util.mapEntitiesToDomain
import com.junemon.gamesapi.core.util.mapFavToDomain
import com.junemon.gamesapi.core.util.mapRemoteGenresDataToDomain
import com.junemon.gamesapi.core.util.mapRemoteSearchDataToDomain
import com.junemon.gamesapi.core.util.mapSingleRemoteDetailGameDataToDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.lang.Exception

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
private const val NETWORK_PAGE_SIZE = 20
private const val EMPTY_DATA = "No Data Available"

@ExperimentalCoroutinesApi
@FlowPreview
class GameRepositoryImpl(
    private val remoteDataSource: GameRemoteDataSource,
    private val pagingRemoteDataSource: GamePaginationRemoteDataSource,
    private val cacheDataSource: GameCacheDataSource
) : GameRepository {

    override fun getCachedListGames(): Flow<ConsumeResult<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return cacheDataSource.getGames().map { it.mapEntitiesToDomain() }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<DataHelper<List<GameResponse>>> {
                return remoteDataSource.getFlowListGames()
            }

            override suspend fun saveCallResult(data: List<GameResponse>) {
                cacheDataSource.saveGames(data)
            }
        }.asFlow().onStart { emit(ConsumeResult.Loading) }
            .onCompletion { emit(ConsumeResult.Complete) }
    }

    override fun getListGamesByGenres(): Flow<ConsumeResult<List<GameGenre>>> = flow {
        when (val response = remoteDataSource.getListGamesByGenres()) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data.mapRemoteGenresDataToDomain()))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
            is DataHelper.RemoteSourceEmpty ->{
                emit(ConsumeResult.ErrorHappen(Exception(EMPTY_DATA)))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }

    override fun getSearchGames(query: String): Flow<ConsumeResult<List<GameSearch>>> = flow {
        when (val response = remoteDataSource.getSearchGames(query)) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data.mapRemoteSearchDataToDomain()))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
            is DataHelper.RemoteSourceEmpty ->{
                emit(ConsumeResult.ErrorHappen(Exception(EMPTY_DATA)))
            }
        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }

    override fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>> = flow {
        when (val response = remoteDataSource.getDetailGames(gameId)) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data.mapSingleRemoteDetailGameDataToDomain()))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }

        }
    }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }

    override fun getPagingListGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pagingRemoteDataSource }
        ).flow
    }

    override suspend fun saveFavoriteGames(data: GameDetail) {
        cacheDataSource.saveFavoriteGames(data)
    }

    override fun getFavoriteGames(): Flow<List<GameFavorite>> {
        return cacheDataSource.getFavoriteGames().map { it.mapFavToDomain() }
    }

    override suspend fun clearFavoriteGameById(id: Int) {
        cacheDataSource.clearFavoriteGameById(id)
    }
}