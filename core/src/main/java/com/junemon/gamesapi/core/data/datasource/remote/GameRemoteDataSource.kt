package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.domain.model.GameData
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameSearch
import com.junemon.gamesapi.core.domain.model.CachedDataHelper
import com.junemon.gamesapi.core.domain.model.DataHelper
import kotlinx.coroutines.flow.Flow

interface GameRemoteDataSource {
    fun getFlowListGames(): Flow<CachedDataHelper<List<GameData>>>

    suspend fun getListGames(): DataHelper<List<GameData>>

    suspend fun getListGamesByGenres(): DataHelper<List<GameGenre>>

    suspend fun getDetailGames(gameId: Int): DataHelper<GameDetail>

    suspend fun getSearchGames(query: String): DataHelper<List<GameSearch>>
}