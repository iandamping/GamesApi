package com.junemon.gamesapi.core.domain.repository

import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.GamesModel

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRepository {
    suspend fun getListGames(): ConsumeResult<GamesModel>

    fun saveCacheGames(data: GamesModel)

    fun getCacheGames(): GamesModel
}