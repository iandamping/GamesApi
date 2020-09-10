package com.junemon.gamesapi.core.data.data.datasource

import com.junemon.model.DataHelper
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import com.junemon.model.games.GameSearch

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRemoteDataSource {
    suspend fun getListGames(): DataHelper<List<GameData>>

    suspend fun getListGamesByGenres(): DataHelper<List<GameGenre>>

    suspend fun getDetailGames(gameId: Int): DataHelper<GameDetail>

    suspend fun getSearchGames(query: String): DataHelper<List<GameSearch>>
}

interface GameCacheDataSource {

    fun saveGames(data: GameData)

    fun getGames(): GameData
}