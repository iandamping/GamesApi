package com.junemon.gamesapi.core.data.datasource.cache

import com.google.gson.Gson
import com.junemon.gamesapi.core.cache.PreferenceHelper
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.model.GamesModel
import com.junemon.gamesapi.util.AppConstant.saveGameKey

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl(private val preferenceHelper: PreferenceHelper) :
    GameCacheDataSource {
    private val gson: Gson by lazy { Gson() }

    override fun saveGames(data: GamesModel) {
        preferenceHelper.saveStringInSharedPreference(
            saveGameKey,
            gson.toJson(data)
        )
    }

    override fun getGames(): GamesModel {
        val results: GamesModel by lazy { gson.fromJson(saveGameKey, GamesModel::class.java) }
        return results
    }
}