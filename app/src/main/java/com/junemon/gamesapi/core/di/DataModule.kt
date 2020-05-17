package com.junemon.gamesapi.core.di

import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.data.data.repository.GameRepositoryImpl
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSourceImpl
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import org.koin.dsl.module

/**
 * Created by Ian Damping on 20,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

val dataSourceModule = module {
    single { GameCacheDataSourceImpl(get()) as GameCacheDataSource }
    single { GameRemoteDataSourceImpl(get()) as GameRemoteDataSource }

}

val repositoryModule = module {
    single {
        GameRepositoryImpl(
            remoteDataSource = get(),
            cacheDataSource = get()
        ) as GameRepository
    }

}

val useCaseModule = module {
    factory { GameUseCase(get()) }
}