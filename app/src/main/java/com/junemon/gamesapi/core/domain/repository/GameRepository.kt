package com.junemon.gamesapi.core.domain.repository

import androidx.lifecycle.LiveData
import com.junemon.model.ConsumeResult
import com.junemon.model.games.GamesModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRepository {
    fun getListGames(): Flow<ConsumeResult<GamesModel>>
    fun saveGames(data: GamesModel)
    fun getCachedGames(): GamesModel
}