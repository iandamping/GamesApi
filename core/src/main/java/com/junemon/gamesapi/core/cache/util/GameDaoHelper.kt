package com.junemon.gamesapi.core.cache.util

import com.junemon.gamesapi.core.cache.model.GameEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameDaoHelper {

    fun loadGame(): Flow<List<GameEntity>>

    suspend fun deleteAllGame()

    suspend fun insertAllGame(vararg tagsData: GameEntity)
}