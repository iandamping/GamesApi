package com.junemon.gamesapi.domain2.usecase

import androidx.lifecycle.LiveData
import com.junemon.gamesapi.domain2.model.GameData
import com.junemon.gamesapi.domain2.repository.GamesRepository
import com.junemon.model.RepositoryDataHelper

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesUseCase(private val gameRepository: GamesRepository) {

    fun get(): LiveData<RepositoryDataHelper<List<GameData>>> = gameRepository.get()
}