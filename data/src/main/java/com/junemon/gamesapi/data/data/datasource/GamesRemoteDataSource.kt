package com.junemon.gamesapi.data.data.datasource

import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.domain2.model.GameModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GamesRemoteDataSource {

    suspend fun get(): ResultToConsume<List<GameModel>>
}

interface GameCacheDataSource {

    suspend fun set(data: List<GameModel>)

    fun get(): Flow<List<GameModel>>
}