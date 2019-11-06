package com.junemon.gamesapi.data.data.repository

import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.data.datasource.cache.ssotResultFlow
import com.junemon.gamesapi.domain2.model.GameModel
import com.junemon.gamesapi.domain2.repository.GamesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

class GamesRepositoryImpl(private val remoteSource: GamesRemoteDataSource, private val localSource: GameCacheDataSource) : GamesRepository {
    @ExperimentalCoroutinesApi
    override fun get(): Flow<ResultToConsume<List<GameModel>>> {
        return ssotResultFlow(
            databaseQuery = { localSource.get() },
            networkCall = { remoteSource.get() },
            saveCallResult = { localSource.set(it) }
            )
    }
}