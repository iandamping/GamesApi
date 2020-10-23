package com.junemon.gamesapi.core.domain.usecase

import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.gamesapi.core.domain.repository.DevelopersRepository
import com.junemon.model.ConsumeResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DevelopersUseCase (private val repository: DevelopersRepository) {

    fun getListDevelopers(): Flow<ConsumeResult<DeveloperData>> = repository.getListDevelopers()
    fun saveStringInSharedPreference(key: String, value: String?) =
        repository.saveStringInSharedPreference(key, value)

    fun getStringInSharedPreference(key: String): String =
        repository.getStringInSharedPreference(key)

    fun saveIntInSharedPreference(key: String, value: Int?) =
        repository.saveIntInSharedPreference(key, value)

    fun getIntInSharedPreference(key: String): Int = repository.getIntInSharedPreference(key)
    fun saveBooleanInSharedPreference(key: String, value: Boolean) =
        repository.saveBooleanInSharedPreference(key, value)

    fun getBooleanInSharedPreference(key: String): Boolean =
        repository.getBooleanInSharedPreference(key)

    fun deleteSharedPreference(key: String) = repository.deleteSharedPreference(key)

    fun deleteAllSharedPrefrence() = repository.deleteAllSharedPrefrence()
}