package com.junemon.gamesapi.core.domain.repository

import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.model.ConsumeResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface DevelopersRepository {
    fun getListDevelopers(): Flow<ConsumeResult<DeveloperData>>
    fun saveStringInSharedPreference(key: String, value: String?)
    fun getStringInSharedPreference(key: String): String
    fun saveIntInSharedPreference(key: String, value: Int?)
    fun getIntInSharedPreference(key: String): Int
    fun saveBooleanInSharedPreference(key: String, value: Boolean)
    fun getBooleanInSharedPreference(key: String): Boolean
    fun deleteSharedPreference(key: String)
    fun deleteAllSharedPrefrence()
}