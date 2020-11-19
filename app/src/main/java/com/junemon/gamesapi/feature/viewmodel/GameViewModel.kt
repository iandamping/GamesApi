package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameViewModel(
    private val useCase: GameUseCase
) : BaseViewModel() {

    fun getCachedListGames() = useCase.getCachedListGames().asLiveData()

    fun getListGamesByGenres() = useCase.getListGamesByGenres().asLiveData()

    fun getDetailGames(data: Int) = useCase.getDetailGames(data).asLiveData()

    fun getSearchGames(query: String) = useCase.getSearchGames(query).asLiveData()

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

    fun getPagingListGames(): Flow<PagingData<Game>> =
        useCase.getPagingListGames().cachedIn(viewModelScope)
}