package com.junemon.gamesapi.core.data.data.datasource

import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.model.CachedDataHelper
import com.junemon.model.DataHelper
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
interface GameRemoteDataSource {
    fun getFlowListGames(): Flow<CachedDataHelper<List<GameData>>>

    suspend fun getListGames(): DataHelper<List<GameData>>

    suspend fun getListGamesByGenres(): DataHelper<List<GameGenre>>

    suspend fun getDetailGames(gameId: Int): DataHelper<GameDetail>

    suspend fun getSearchGames(query: String): DataHelper<List<GameSearch>>
}

interface GameCacheDataSource {

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

    suspend fun saveGames(data: List<GameData>)

    fun getGames(): Flow<List<GameEntity>>

    suspend fun clearAllGames()
}