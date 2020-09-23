package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.core.cache.util.GameDaoHelper
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.model.games.GameData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl @Inject constructor(
    private val gameDaoHelper: GameDaoHelper
) :
    GameCacheDataSource {
    override suspend fun saveGames(data: List<GameData>) {
        gameDaoHelper.insertAllGame(*data.map {
            GameEntity(
                gameId = it.id,
                gameName = it.name,
                gameGenre = it.genres?.get(0)?.name,
                gameImage = it.backgroundImage
            )
        }.toTypedArray())
    }

    override fun getGames(): Flow<List<GameEntity>> {
        return gameDaoHelper.loadGame()
    }

    override suspend fun clearAllGames() {
        gameDaoHelper.deleteAllGame()
    }


}