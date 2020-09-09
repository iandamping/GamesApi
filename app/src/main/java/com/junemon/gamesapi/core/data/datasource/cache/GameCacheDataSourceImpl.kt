package com.junemon.gamesapi.core.data.datasource.cache

import com.google.gson.Gson
import com.junemon.gamesapi.core.cache.PreferenceHelper
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.model.games.GameData
import com.junemon.gamesapi.util.AppConstant.saveGameKey
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val gson: Gson
) :
    GameCacheDataSource {

    override fun saveGames(data: GameData) {
        preferenceHelper.saveStringInSharedPreference(
            saveGameKey,
            gson.toJson(data)
        )
    }

    override fun getGames(): GameData {
        val results: GameData by lazy { gson.fromJson(preferenceHelper.getStringInSharedPreference(saveGameKey), GameData::class.java) }
        return results
    }
}