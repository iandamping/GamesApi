package com.junemon.gamesapi.core.cache.util

import com.junemon.gamesapi.core.cache.dao.GameDao
import com.junemon.gamesapi.core.cache.model.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameDaoHelperImpl @Inject constructor(private val dao: GameDao) : GameDaoHelper {
    override fun loadGame(): Flow<List<GameEntity>> {
        return dao.loadGame()
    }

    override suspend fun deleteAllGame() {
        dao.deleteAllGame()
    }

    override suspend fun insertAllGame(vararg tagsData: GameEntity) {
        dao.insertAllGame(*tagsData)
    }
}