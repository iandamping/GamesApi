package com.junemon.gamesapi.favorite.feature.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameFavoriteViewModel(private val useCase: GameUseCase): ViewModel() {

    fun getFavoriteGames() = useCase.getFavoriteGames().asLiveData()

    fun clearFavoriteGameById(id: Int?) {
        viewModelScope.launch {
            if (id != null) useCase.clearFavoriteGameById(id)
        }
    }

    fun saveFavoriteGames(data: GameDetail?) {
        viewModelScope.launch {
            if (data != null) useCase.saveFavoriteGames(data)
        }
    }
}