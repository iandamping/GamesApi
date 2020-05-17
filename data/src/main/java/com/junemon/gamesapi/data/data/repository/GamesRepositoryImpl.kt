package com.junemon.gamesapi.data.data.repository

import androidx.lifecycle.LiveData
import com.junemon.gamesapi.data.base.ExtractResult
import com.junemon.gamesapi.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.domain2.model.GameData
import com.junemon.gamesapi.domain2.repository.GamesRepository
import com.junemon.model.RepositoryDataHelper

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

class GamesRepositoryImpl(
    private val remoteSource: GamesRemoteDataSource,
    private val localSource: GameCacheDataSource,
    private val cachingResult: ExtractResult
) : GamesRepository {

    override fun get(): LiveData<RepositoryDataHelper<List<GameData>>> {
        // return flowResult { remoteSource.get()  }
        return cachingResult.ssotLiveDataResult(
            databaseQuery = { localSource.get() },
            networkCall = { remoteSource.get() },
            saveCallResult = { localSource.set(it) }
        )
    }
}