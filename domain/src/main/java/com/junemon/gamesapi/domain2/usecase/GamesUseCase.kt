package com.junemon.gamesapi.domain2.usecase

import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.domain2.model.GameModel
import com.junemon.gamesapi.domain2.repository.GamesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesUseCase(private val gameRepository: GamesRepository) {

    fun get(): Flow<ResultToConsume<List<GameModel>>> = gameRepository.get()
}