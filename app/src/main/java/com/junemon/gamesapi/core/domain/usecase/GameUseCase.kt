package com.junemon.gamesapi.core.domain.usecase

import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.GamesModel

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameUseCase(private val repository: GameRepository) {

    suspend fun getListGames(): ConsumeResult<GamesModel> = repository.getListGames()

    fun saveCacheGames(data: GamesModel) = repository.saveCacheGames(data)

    fun getCacheGames(): GamesModel = repository.getCacheGames()
}