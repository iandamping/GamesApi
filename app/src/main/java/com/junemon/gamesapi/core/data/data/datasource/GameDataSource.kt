package com.junemon.gamesapi.core.data.data.datasource

import com.junemon.gamesapi.core.model.DataHelper
import com.junemon.gamesapi.core.model.GamesModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRemoteDataSource {
    suspend fun getListGames(): Flow<DataHelper<List<GamesModel>>>
}

interface GameCacheDataSource {

    fun saveGames(data: GamesModel)

    fun getGames(): GamesModel
}