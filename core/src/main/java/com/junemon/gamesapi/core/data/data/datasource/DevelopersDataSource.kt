package com.junemon.gamesapi.core.data.data.datasource

import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.model.DataHelper
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface DevelopersRemoteDataSource{

    suspend fun getListDevelopers(): DataHelper<List<DeveloperData>>
}

interface DevelopersCacheDataSource{

    fun saveStringInSharedPreference(key: String, value: String?)

    fun getStringInSharedPreference(key: String): String

    fun saveIntInSharedPreference(key: String, value: Int?)

    fun getIntInSharedPreference(key: String): Int

    fun saveBooleanInSharedPreference(key: String, value: Boolean)

    fun getBooleanInSharedPreference(key: String): Boolean

    fun deleteSharedPreference(key: String)

    fun deleteAllSharedPrefrence()

}