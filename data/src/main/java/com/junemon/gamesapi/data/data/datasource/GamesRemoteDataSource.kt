package com.junemon.gamesapi.data.data.datasource

import com.junemon.gamesapi.domain2.model.GameData
import com.junemon.model.DataHelper
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GamesRemoteDataSource {

    suspend fun get(): DataHelper<List<GameData>>
}

interface GameCacheDataSource {

    suspend fun set(data: List<GameData>)

    fun get(): Flow<List<GameData>>
}