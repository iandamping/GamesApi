package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.core.cache.preference.PreferenceHelper
import com.junemon.gamesapi.core.cache.preference.listener.BooleanPrefValueListener
import com.junemon.gamesapi.core.cache.preference.listener.IntPrefValueListener
import com.junemon.gamesapi.core.cache.preference.listener.StringPrefValueListener
import com.junemon.gamesapi.core.cache.util.GameDaoHelper
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.model.GameData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl (
    private val gameDaoHelper: GameDaoHelper,
    private val preferenceHelper: PreferenceHelper,
    private val stringListener: StringPrefValueListener,
    private val intListener: IntPrefValueListener,
    private val booleanListener: BooleanPrefValueListener
) :
    GameCacheDataSource {

    override fun registerPreferenceListener() {
        preferenceHelper.apply {
            registerListener(intListener)
            registerListener(stringListener)
            registerListener(booleanListener)
        }

    }

    override fun unregisterPreferenceListener() {
        preferenceHelper.apply {
            unregisterListener(intListener)
            unregisterListener(stringListener)
            unregisterListener(booleanListener)
        }

    }

    override fun saveStringInSharedPreference(key: String, value: String?) {
        preferenceHelper.saveStringInSharedPreference(key, value)
    }

    override fun getStringInSharedPreference(key: String): Flow<String?> {
        stringListener.setListenKey(key)
        return flowOf(preferenceHelper.getStringInSharedPreference(key))
            .combine(stringListener.stringPreferenceValue) { a, b ->
                combineString(a, b)
            }
    }

    override fun saveIntInSharedPreference(key: String, value: Int?) {
        preferenceHelper.saveIntInSharedPreference(key, value)
    }

    override fun getIntInSharedPreference(key: String): Flow<Int?> {
        intListener.setListenKey(key)
        return flowOf(preferenceHelper.getIntInSharedPreference(key))
            .combine(intListener.intPreferenceValue) { a, b ->
                combineInt(a, b)
            }
    }

    override fun saveBooleanInSharedPreference(key: String, value: Boolean) {
        preferenceHelper.saveBooleanInSharedPreference(key, value)
    }

    override fun getBooleanInSharedPreference(key: String): Flow<Boolean> {
        booleanListener.setListenKey(key)
        return flowOf(preferenceHelper.getBooleanInSharedPreference(key))
            .combine(booleanListener.boolPreferenceValue) { a, b ->
                combineBoolean(a, b)
            }
    }

    override fun deleteSharedPreference(key: String) {
        preferenceHelper.deleteAllSharedPrefrence()
    }

    override fun deleteAllSharedPrefrence() {
        preferenceHelper.deleteAllSharedPrefrence()
    }

    private fun combineString(data1: String, data2: String?): String {
        return data2 ?: data1
    }

    private fun combineInt(data1: Int, data2: Int?): Int {
        return data2 ?: data1
    }

    private fun combineBoolean(data1: Boolean, data2: Boolean?): Boolean {
        return data2 ?: data1
    }

    override suspend fun saveGames(data: List<GameData>) {
        gameDaoHelper.insertAllGame(*data.map {
            GameEntity(
                gameId = it.id,
                gameName = it.name,
                gameGenre = it.genres?.get(0)?.name,
                gameImage = it.backgroundImage
            )
        }.toTypedArray())
    }

    override fun getGames(): Flow<List<GameEntity>> {
        return gameDaoHelper.loadGame()
    }

    override suspend fun clearAllGames() {
        gameDaoHelper.deleteAllGame()
    }
}