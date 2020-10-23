package com.junemon.gamesapi.core.domain.repository

import androidx.paging.PagingData
import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.model.ConsumeCacheResult
import com.junemon.model.ConsumeResult
import com.junemon.model.GenericPair
import com.junemon.gamesapi.core.data.model.GameData
import com.junemon.gamesapi.core.data.model.GameDetail
import com.junemon.gamesapi.core.data.model.GameGenre
import com.junemon.gamesapi.core.data.model.GameSearch
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GameRepository {
    fun getCachedListGames(): Flow<ConsumeCacheResult<GameEntity>>
    fun getListGames(): Flow<ConsumeResult<GameData>>
    fun getListGamesByGenres(): Flow<ConsumeResult<GameGenre>>
    fun getGenreAndGames():Flow<GenericPair<ConsumeResult<GameData>,ConsumeResult<GameGenre>>>
    fun getSearchGames(query:String): Flow<ConsumeResult<GameSearch>>
    fun getDetailGames(gameId: Int): Flow<ConsumeResult<GameDetail>>
    fun getPagingListGames(): Flow<PagingData<GameData>>
    fun registerPreferenceListener()
    fun unregisterPreferenceListener()
    fun saveStringInSharedPreference(key: String, value: String?)
    fun getStringInSharedPreference(key: String): Flow<String?>
    fun saveIntInSharedPreference(key: String, value: Int?)
    fun getIntInSharedPreference(key: String): Flow<Int?>
    fun saveBooleanInSharedPreference(key: String, value: Boolean)
    fun getBooleanInSharedPreference(key: String): Flow<Boolean>
    fun deleteSharedPreference(key: String)
    fun deleteAllSharedPrefrence()
}