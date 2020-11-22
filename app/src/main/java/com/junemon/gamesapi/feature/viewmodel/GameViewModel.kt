package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.gamesapi.core.util.search
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class GameViewModel(
    private val useCase: GameUseCase
) : BaseViewModel() {

    private val _querySharedFlow =
        MutableSharedFlow<String>() // shared flow for replacing BroadcastChannel

    suspend fun searchGames(query: String) {
        _querySharedFlow.emit(query) // suspends until all subscribers receive it
    }

    val searchResult = _querySharedFlow.asSharedFlow()
        .search {
            useCase.getSearchGames(it)
        }

    fun getCachedListGames() = useCase.getCachedListGames().asLiveData()

    fun getListGamesByGenres() = useCase.getListGamesByGenres().asLiveData()

    fun getDetailGames(data: Int) = useCase.getDetailGames(data).asLiveData()

    // fun getSearchGames(query: String) = useCase.getSearchGames(query).asLiveData()

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