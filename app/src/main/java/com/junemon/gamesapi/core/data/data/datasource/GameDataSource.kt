package com.junemon.gamesapi.core.data.data.datasource

import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.model.CachedDataHelper
import com.junemon.model.DataHelper
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import com.junemon.model.games.GameSearch
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRemoteDataSource {
    fun getFlowListGames(): Flow<CachedDataHelper<List<GameData>>>

    suspend fun getListGames(): DataHelper<List<GameData>>

    suspend fun getListGamesByGenres(): DataHelper<List<GameGenre>>

    suspend fun getDetailGames(gameId: Int): DataHelper<GameDetail>

    suspend fun getSearchGames(query: String): DataHelper<List<GameSearch>>
}

interface GameCacheDataSource {

    suspend fun saveGames(data: List<GameData>)

    fun getGames(): Flow<List<GameEntity>>

    suspend fun clearAllGames()
}