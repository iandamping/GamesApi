package com.junemon.gamesapi.core.domain.repository

import androidx.paging.PagingData
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity
import com.junemon.gamesapi.core.domain.model.ConsumeCacheResult
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameGenreResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameSearch
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRepository {
    fun getCachedListGames(): Flow<ConsumeCacheResult<Game>>
    fun getListGamesByGenres(): Flow<ConsumeResult<GameGenre>>
    fun getSearchGames(query:String): Flow<ConsumeResult<GameSearch>>
    fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>>
    fun getPagingListGames(): Flow<PagingData<Game>>
    suspend fun saveFavoriteGames(data: GameDetail)
    fun getFavoriteGames():Flow<List<GameFavorite>>
    suspend fun clearFavoriteGameById(id:Int)

}