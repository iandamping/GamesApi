package com.junemon.gamesapi.core.data.data.repository

import androidx.lifecycle.liveData
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.di.module.DefaultDispatcher
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.DataHelper
import com.junemon.gamesapi.core.model.GamesModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: GameRemoteDataSource,
    private val cacheDataSource: GameCacheDataSource
) : GameRepository {


    override fun getListGames() = liveData(defaultDispatcher) {
        when (val response = remoteDataSource.getListGames()) {
            is DataHelper.RemoteSourceValue -> {
                emit(ConsumeResult.ConsumeData(response.data))
            }
            is DataHelper.RemoteSourceError -> {
                emit(ConsumeResult.ErrorHappen(response.exception))
            }
        }
    }

    override fun saveGames(data: GamesModel) {
        cacheDataSource.saveGames(data)
    }

    override fun getCachedGames(): GamesModel {
        return cacheDataSource.getGames()
    }
}