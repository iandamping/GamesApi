package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import com.junemon.gamesapi.core.domain.model.DataHelper
import kotlinx.coroutines.flow.Flow

interface GameRemoteDataSource {

    fun getFlowListGames(): Flow<DataHelper<List<GameResponse>>>

    suspend fun getDetailGames(gameId: Int): DataHelper<GameDetailResponse>

    suspend fun getSearchGames(query: String): DataHelper<List<GameSearchResponse>>
}