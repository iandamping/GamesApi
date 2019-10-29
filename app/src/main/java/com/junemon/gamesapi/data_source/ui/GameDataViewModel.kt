package com.junemon.gamesapi.data_source.ui

import com.junemon.gamesapi.base.BaseViewModel
import com.junemon.gamesapi.data_source.remote.GamesDataRepository


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameDataViewModel(private val repository: GamesDataRepository) : BaseViewModel() {

    fun getListGames() = repository.getListGames()

}