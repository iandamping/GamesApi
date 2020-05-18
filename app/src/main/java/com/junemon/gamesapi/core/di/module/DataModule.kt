package com.junemon.gamesapi.core.di.module

import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.data.data.repository.GameRepositoryImpl
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSourceImpl
import com.junemon.gamesapi.core.domain.repository.GameRepository
import dagger.Binds
import dagger.Module


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Module
abstract class DataModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: GameRemoteDataSourceImpl): GameRemoteDataSource

    @Binds
    abstract fun bindRepository(repository: GameRepositoryImpl): GameRepository
}