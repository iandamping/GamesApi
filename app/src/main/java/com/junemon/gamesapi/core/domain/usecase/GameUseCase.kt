package com.junemon.gamesapi.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.core.cache.preference.listener.BaseSharedPreferenceListener
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.model.ConsumeCacheResult
import com.junemon.model.ConsumeResult
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameUseCase @Inject constructor(private val repository: GameRepository) {

    fun getListGames(): LiveData<ConsumeResult<GameData>> = repository.getListGames().asLiveData()

    fun getCachedListGames(): LiveData<ConsumeCacheResult<GameEntity>> =
        repository.getCachedListGames().asLiveData()

    fun getListGamesByGenres(): LiveData<ConsumeResult<GameGenre>> =
        repository.getListGamesByGenres().asLiveData()

    fun getGenreAndGames() = repository.getGenreAndGames().asLiveData()

    fun getSearchGames(query: String) = repository.getSearchGames(query).asLiveData()

    fun getDetailGames(gameId: Int): LiveData<ConsumeResult<GameDetail>> =
        repository.getDetailGames(gameId).asLiveData()

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