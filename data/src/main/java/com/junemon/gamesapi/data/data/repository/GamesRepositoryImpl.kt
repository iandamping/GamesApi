package com.junemon.gamesapi.data.data.repository

import androidx.lifecycle.LiveData
import com.ian.app.helper.data.ResultToConsume
import com.ian.app.helper.data.resultLiveData
import com.ian.app.helper.data.ssotResultLiveData
import com.junemon.gamesapi.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.domain2.model.GameModel
import com.junemon.gamesapi.domain2.repository.GamesRepository

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

class GamesRepositoryImpl(private val remoteSource: GamesRemoteDataSource,private val localSource: GameCacheDataSource) : GamesRepository {
    override fun get(): LiveData<ResultToConsume<List<GameModel>>> {

        return ssotResultLiveData(
            databaseQuery = {localSource.get()},
            networkCall = {remoteSource.get()},
            saveCallResult = {localSource.set(it)}
            )
    }
}