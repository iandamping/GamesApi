package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.data.data.datasource.DevelopersRemoteDataSource
import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.gamesapi.core.network.ApiInterface
import com.junemon.gamesapi.core.network.BaseSources
import com.junemon.model.DataHelper
import com.junemon.model.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DevelopersRemoteDataSourceImpl(
    private val api: ApiInterface,
    private val defaultDispatcher: CoroutineDispatcher
) : BaseSources(),DevelopersRemoteDataSource {


    override suspend fun getListDevelopers(): DataHelper<List<DeveloperData>> {
        return withContext(defaultDispatcher){
            when(val responses = oneShotCalls { api.getListDevelopers() }){
                is Results.Success -> {
                    DataHelper.RemoteSourceValue(responses.data.data)
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }
    }
}