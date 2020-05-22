package com.junemon.gamesapi.core.domain.usecase

import androidx.lifecycle.LiveData
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.GamesModel
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameUseCase @Inject constructor(private val repository: GameRepository) {

    fun getListGames(): LiveData<ConsumeResult<GamesModel>> = repository.getListGames()

    fun saveGames(data: GamesModel) = repository.saveGames(data)

    fun getCachedGames(): GamesModel = repository.getCachedGames()

}