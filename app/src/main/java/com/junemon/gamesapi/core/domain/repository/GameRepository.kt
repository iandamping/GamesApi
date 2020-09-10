package com.junemon.gamesapi.core.domain.repository

import com.junemon.model.ConsumeResult
import com.junemon.model.GenericPair
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
interface GameRepository {
    fun getListGames(): Flow<ConsumeResult<GameData>>
    fun getListGamesByGenres(): Flow<ConsumeResult<GameGenre>>
    fun getGenreAndGames():Flow<GenericPair<ConsumeResult<GameData>,ConsumeResult<GameGenre>>>
    fun getSearchGames(query:String): Flow<ConsumeResult<GameSearch>>
    fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>>
    fun saveGames(data: GameData)
    fun getCachedGames(): GameData
}