package com.junemon.gamesapi.core.data.data.repository

import com.junemon.gamesapi.core.data.data.datasource.DevelopersCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.DevelopersRemoteDataSource
import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.gamesapi.core.domain.repository.DevelopersRepository
import com.junemon.model.ConsumeResult
import com.junemon.model.DataHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DevelopersRepositoryImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: DevelopersRemoteDataSource,
    private val cacheDataSource: DevelopersCacheDataSource
) : DevelopersRepository {

    override fun getListDevelopers(): Flow<ConsumeResult<DeveloperData>> {
        return flow {
            when (val response = remoteDataSource.getListDevelopers()) {
                is DataHelper.RemoteSourceValue -> {
                    emit(ConsumeResult.ConsumeData(response.data))
                }
                is DataHelper.RemoteSourceError -> {
                    emit(ConsumeResult.ErrorHappen(response.exception))
                }
            }
        }.onStart { emit(ConsumeResult.Loading) }.onCompletion { emit(ConsumeResult.Complete) }
            .flowOn(defaultDispatcher).conflate()
    }

    override fun saveStringInSharedPreference(key: String, value: String?) {
        cacheDataSource.saveStringInSharedPreference(key, value)
    }

    override fun getStringInSharedPreference(key: String): String {
        return cacheDataSource.getStringInSharedPreference(key)
    }

    override fun saveIntInSharedPreference(key: String, value: Int?) {
        cacheDataSource.saveIntInSharedPreference(key, value)
    }

    override fun getIntInSharedPreference(key: String): Int {
        return cacheDataSource.getIntInSharedPreference(key)
    }

    override fun saveBooleanInSharedPreference(key: String, value: Boolean) {
        cacheDataSource.saveBooleanInSharedPreference(key, value)
    }

    override fun getBooleanInSharedPreference(key: String): Boolean {
        return cacheDataSource.getBooleanInSharedPreference(key)
    }

    override fun deleteSharedPreference(key: String) {
        cacheDataSource.deleteSharedPreference(key)
    }

    override fun deleteAllSharedPrefrence() {
        cacheDataSource.deleteAllSharedPrefrence()
    }
}