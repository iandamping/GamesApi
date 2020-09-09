package com.junemon.gamesapi.core.data.data.datasource

import com.junemon.model.DataHelper
import com.junemon.model.games.GameData

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRemoteDataSource {
    suspend fun getListGames(): DataHelper<List<GameData>>
}

interface GameCacheDataSource {

    fun saveGames(data: GameData)

    fun getGames(): GameData
}