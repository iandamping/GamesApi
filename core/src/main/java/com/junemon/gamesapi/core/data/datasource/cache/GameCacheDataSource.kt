package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameCacheDataSource {

    suspend fun saveGames(data: List<GameResponse>)

    suspend fun saveFavoriteGames(data: GameDetail)

    fun getGames(): Flow<List<GameEntity>>

    fun getFavoriteGames():Flow<List<GameFavoriteEntity>>

    suspend fun clearAllCachedGames()

    suspend fun clearFavoriteGameById(id:Int)
}