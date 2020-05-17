package com.junemon.gamesapi.core.data.data.repository

import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.DataHelper
import com.junemon.gamesapi.core.model.GamesModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.collect

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRepositoryImpl(
    private val remoteDataSource: GameRemoteDataSource,
    private val cacheDataSource: GameCacheDataSource
) : GameRepository {

    override suspend fun getListGames(): ConsumeResult<GamesModel> {
        val result: CompletableDeferred<ConsumeResult<GamesModel>> = CompletableDeferred()
        remoteDataSource.getListGames().collect {
            when (it) {
                is DataHelper.RemoteSourceValue -> {
                    result.complete(ConsumeResult.ConsumeData(it.data))
                }
                is DataHelper.RemoteSourceError -> {
                    result.complete(ConsumeResult.ErrorHappen(it.exception))
                }
            }
        }
        return result.await()
    }

    override fun saveCacheGames(data: GamesModel) {
        cacheDataSource.saveGames(data)
    }

    override fun getCacheGames(): GamesModel {
        return cacheDataSource.getGames()
    }
}