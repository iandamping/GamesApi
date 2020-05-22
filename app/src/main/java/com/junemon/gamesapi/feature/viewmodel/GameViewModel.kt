package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.ViewModel
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.gamesapi.core.model.GamesModel
import javax.inject.Inject


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameViewModel @Inject constructor(private val repo:GameUseCase): ViewModel() {

    fun getGames() = repo.getListGames()

    fun saveGames(data:GamesModel) = repo.saveGames(data)

    fun getCachedGame() = repo.getCachedGames()

}