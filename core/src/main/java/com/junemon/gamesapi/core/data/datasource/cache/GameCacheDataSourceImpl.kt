package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.room.GameDao
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.util.mapRemoteDataToCache
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl(
    private val gameDaoHelper: GameDao
) : GameCacheDataSource {

    override suspend fun saveGames(data: List<GameResponse>) {
        gameDaoHelper.insertAllGame(*data.map {
            it.mapRemoteDataToCache()
        }.toTypedArray())
    }

    override fun getGames(): Flow<List<GameEntity>> {
        return gameDaoHelper.loadGame()
    }

    override suspend fun clearAllGames() {
        gameDaoHelper.deleteAllGame()
    }
}