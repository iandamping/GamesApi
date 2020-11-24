package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity
import com.junemon.gamesapi.core.data.datasource.cache.preference.value.ProvideValue
import com.junemon.gamesapi.core.data.datasource.cache.room.GameDao
import com.junemon.gamesapi.core.data.datasource.cache.room.GameFavoriteDao
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.util.mapDomainToData
import com.junemon.gamesapi.core.util.mapRemoteDataToCache
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl(
    private val valueProvider: ProvideValue,
    private val gameDao: GameDao,
    private val gameFavoriteDao: GameFavoriteDao
) : GameCacheDataSource {

    override suspend fun saveGames(data: List<GameResponse>) {
        gameDao.insertAllGame(*data.map {
            it.mapRemoteDataToCache()
        }.toTypedArray())
    }

    override suspend fun saveFavoriteGames(data: GameDetail) {
        gameFavoriteDao.insertFavoriteGame(data.mapDomainToData())
    }

    override fun getGames(): Flow<List<GameEntity>> {
        return gameDao.loadGame()
    }

    override  fun getFavoriteGames():Flow<List<GameFavoriteEntity>>{
        return gameFavoriteDao.loadFavoriteGame()
    }

    override suspend fun clearAllCachedGames() {
        gameDao.deleteAllGame()
    }

    override suspend fun clearFavoriteGameById(id: Int) {
        gameFavoriteDao.deleteFavoriteGameById(id)
    }

    override fun getFavoriteUri(): String {
        return valueProvider.provideFavoriteUriValue()
    }
}