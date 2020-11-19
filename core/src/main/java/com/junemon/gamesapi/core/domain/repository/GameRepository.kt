package com.junemon.gamesapi.core.domain.repository

import androidx.paging.PagingData
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.domain.model.ConsumeCacheResult
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.GameRemoteData
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameSearch
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRepository {
    fun getCachedListGames(): Flow<ConsumeCacheResult<GameEntity>>
    fun getListGamesByGenres(): Flow<ConsumeResult<GameGenre>>
    fun getSearchGames(query:String): Flow<ConsumeResult<GameSearch>>
    fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>>
    fun getPagingListGames(): Flow<PagingData<GameRemoteData>>

}