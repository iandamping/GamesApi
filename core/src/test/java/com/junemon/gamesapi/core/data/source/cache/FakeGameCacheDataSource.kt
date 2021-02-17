package com.junemon.gamesapi.core.data.source.cache

import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSource
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.util.mapDomainToData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ian Damping on 01,December,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

class FakeGameCacheDataSource(
    val fakeSaveGame: MutableList<GameResponse>? = mutableListOf(),
    val fakeGame: MutableList<GameEntity>? = mutableListOf(),
    val fakeGameFavorite: MutableList<GameFavoriteEntity>? = mutableListOf(),
) :
    GameCacheDataSource {

    override suspend fun saveGames(data: List<GameResponse>) {
        fakeSaveGame?.addAll(data)
    }

    override suspend fun saveFavoriteGames(data: GameDetail) {
        fakeGameFavorite?.add(
            data.mapDomainToData()
        )
    }

    override fun getGames(): Flow<List<GameEntity>> {
        return flow { fakeGame?.let { emit(it) } }
    }

    override fun getFavoriteGames(): Flow<List<GameFavoriteEntity>> {
        return flow { fakeGameFavorite?.let { emit(it) } }
    }

    override suspend fun clearAllCachedGames() {
        fakeGame?.clear()
    }

    override suspend fun clearFavoriteGameById(id: Int) {
        fakeGameFavorite?.removeAt(id)
    }
}