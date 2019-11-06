package com.junemon.gamesapi.data.data.repository

import androidx.lifecycle.LiveData
import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.data.datasource.cache.ssotResultFlowLiveDataResult
import com.junemon.gamesapi.domain2.model.GameModel
import com.junemon.gamesapi.domain2.repository.GamesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

class GamesRepositoryImpl(
    private val remoteSource: GamesRemoteDataSource,
    private val localSource: GameCacheDataSource
) : GamesRepository {
    @ExperimentalCoroutinesApi
    override fun get(): LiveData<ResultToConsume<List<GameModel>>> {
        // return flowResult { remoteSource.get()  }
        return ssotResultFlowLiveDataResult(
            databaseQuery = { localSource.get() },
            networkCall = { remoteSource.get() },
            saveCallResult = { localSource.set(it) }
        )
    }
}