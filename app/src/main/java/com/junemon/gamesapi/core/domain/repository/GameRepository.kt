package com.junemon.gamesapi.core.domain.repository

import com.junemon.model.ConsumeResult
import com.junemon.model.games.GameData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRepository {
    fun getListGames(): Flow<ConsumeResult<GameData>>
    fun saveGames(data: GameData)
    fun getCachedGames(): GameData
}