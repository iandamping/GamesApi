package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.*
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.model.GenericPair
import com.junemon.model.games.GameData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameViewModel @Inject constructor(private val repo:GameUseCase): BaseViewModel() {

    fun getGames() = repo.getListGames()

    fun getListGamesByGenres() = repo.getListGamesByGenres()

    fun getDetailGames(data:Int) = repo.getDetailGames(data)

    fun getGenreAndGames() = repo.getGenreAndGames()

    fun saveGames(data: GameData) = repo.saveGames(data)

    fun getCachedGame() = repo.getCachedGames()


}