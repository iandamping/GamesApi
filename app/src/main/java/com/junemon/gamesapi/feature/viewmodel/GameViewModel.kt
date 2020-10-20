package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.model.games.GameData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameViewModel @Inject constructor(
    private val repo: GameUseCase
) : BaseViewModel() {

    fun getCachedListGames() = repo.getCachedListGames().asLiveData()

    fun getListGames() = repo.getListGames().asLiveData()

    fun getListGamesByGenres() = repo.getListGamesByGenres().asLiveData()

    fun getDetailGames(data: Int) = repo.getDetailGames(data).asLiveData()

    fun getSearchGames(query: String) = repo.getSearchGames(query).asLiveData()

    fun getGenreAndGames() = repo.getGenreAndGames()

    fun getPagingListGames():Flow<PagingData<GameData>>{
        return repo.getPagingListGames().cachedIn(viewModelScope)
    }
}