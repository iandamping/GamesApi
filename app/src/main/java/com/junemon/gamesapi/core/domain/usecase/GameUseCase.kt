package com.junemon.gamesapi.core.domain.usecase

import androidx.paging.PagingData
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.model.games.GameData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameUseCase @Inject constructor(private val repository: GameRepository) {

    fun getListGames() = repository.getListGames()

    fun getCachedListGames() = repository.getCachedListGames()

    fun getListGamesByGenres() = repository.getListGamesByGenres()

    fun getGenreAndGames() = repository.getGenreAndGames()

    fun getSearchGames(query: String) = repository.getSearchGames(query)

    fun getDetailGames(gameId: Int) = repository.getDetailGames(gameId)

    fun getPagingListGames(): Flow<PagingData<GameData>> = repository.getPagingListGames()

    fun registerPreferenceListener() =
        repository.registerPreferenceListener()

    fun unregisterPreferenceListener() =
        repository.unregisterPreferenceListener()

    fun saveStringInSharedPreference(key: String, value: String?) =
        repository.saveStringInSharedPreference(key, value)

    fun getStringInSharedPreference(key: String): Flow<String?> =
        repository.getStringInSharedPreference(key)

    fun saveIntInSharedPreference(key: String, value: Int?) =
        repository.saveIntInSharedPreference(key, value)

    fun getIntInSharedPreference(key: String): Flow<Int?> = repository.getIntInSharedPreference(key)
    fun saveBooleanInSharedPreference(key: String, value: Boolean) =
        repository.saveBooleanInSharedPreference(key, value)

    fun getBooleanInSharedPreference(key: String): Flow<Boolean> =
        repository.getBooleanInSharedPreference(key)

    fun deleteSharedPreference(key: String) = repository.deleteSharedPreference(key)

    fun deleteAllSharedPrefrence() = repository.deleteAllSharedPrefrence()
}