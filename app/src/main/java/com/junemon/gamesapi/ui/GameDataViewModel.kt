package com.junemon.gamesapi.ui

import com.ian.app.helper.base.BaseViewModel
import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.domain2.model.GameModel
import com.junemon.gamesapi.domain2.usecase.GamesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameDataViewModel(private val repository: GamesUseCase) : BaseViewModel() {

    fun get(data:(ResultToConsume<List<GameModel>>) -> Unit){
        vmScope.launch {
            repository.get().collect {
                data(it)
            }
        }
    }
}