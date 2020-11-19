package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.domain.model.GameData
import kotlinx.coroutines.flow.Flow

interface GameCacheDataSource {

    suspend fun saveGames(data: List<GameData>)

    fun getGames(): Flow<List<GameEntity>>

    suspend fun clearAllGames()
}