package com.junemon.gamesapi.core.module

import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSource
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSourceImpl
import com.junemon.gamesapi.core.data.repository.GameRepositoryImpl
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.gamesapi.core.domain.usecase.GameUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Ian Damping on 20,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

@ExperimentalCoroutinesApi
val repositoryModule = module {
    single<GameRemoteDataSource> {
        GameRemoteDataSourceImpl(
            api = get(),
            defaultDispatcher = get(named("default"))
        )
    }
    single<GameCacheDataSource> {
        GameCacheDataSourceImpl(
            get(),get(), get()
        )
    }

    single<GameRepository> {
        GameRepositoryImpl(
            remoteDataSource = get(),
            pagingRemoteDataSource = get(),
            cacheDataSource = get()
        )
    }
}

val useCaseModule = module {
    factory<GameUseCase> {
        GameUseCaseImpl(
            repository = get()
        )
    }
}

