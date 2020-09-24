package com.junemon.gamesapi.feature.viewmodel

import com.junemon.gamesapi.core.domain.usecase.GameUseCase
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

    init {
        repo.registerPreferenceListener()
    }

    fun observeStringPreference(key: String): Flow<String?> {
        return repo.getStringInSharedPreference(key)
    }

    fun setStringPreferenceValue(key: String, value: String) =
        repo.saveStringInSharedPreference(key, value)

    fun getCachedListGames() = repo.getCachedListGames()

    fun getListGamesByGenres() = repo.getListGamesByGenres()

    fun getDetailGames(data: Int) = repo.getDetailGames(data)

    fun getSearchGames(query: String) = repo.getSearchGames(query)

    fun getGenreAndGames() = repo.getGenreAndGames()

    override fun onCleared() {
        super.onCleared()
        repo.unregisterPreferenceListener()
    }
}