package com.junemon.gamesapi.core.data.data.repository

import androidx.lifecycle.liveData
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.di.module.MainDispatcher
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.DataHelper
import com.junemon.gamesapi.core.model.GamesModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRepositoryImpl @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val remoteDataSource: GameRemoteDataSource
) : GameRepository {

    override fun getListGames() = liveData(mainDispatcher) {
        remoteDataSource.getListGames().collect {
            when (it) {
                is DataHelper.RemoteSourceValue -> {
                    emit(ConsumeResult.ConsumeData(it.data))
                }
                is DataHelper.RemoteSourceError -> {
                    emit(ConsumeResult.ErrorHappen(it.exception))
                }
            }
        }
    }
}