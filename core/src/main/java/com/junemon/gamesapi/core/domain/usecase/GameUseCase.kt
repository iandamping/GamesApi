package com.junemon.gamesapi.core.domain.usecase

import androidx.paging.PagingData
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.core.domain.model.GameSearch
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameUseCase {
    fun getCachedListGames(): Flow<ConsumeResult<List<Game>>>
    fun getSearchGames(query: String): Flow<ConsumeResult<List<GameSearch>>>
    fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>>
    fun getPagingListGames(): Flow<PagingData<Game>>
    suspend fun saveFavoriteGames(data: GameDetail)
    fun getFavoriteGames(): Flow<List<GameFavorite>>
    suspend fun clearFavoriteGameById(id: Int)
    fun getFavoriteUri():String
}