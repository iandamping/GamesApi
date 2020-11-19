package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import kotlinx.coroutines.flow.Flow

interface GameCacheDataSource {

    suspend fun saveGames(data: List<GameResponse>)

    fun getGames(): Flow<List<GameEntity>>

    suspend fun clearAllGames()
}