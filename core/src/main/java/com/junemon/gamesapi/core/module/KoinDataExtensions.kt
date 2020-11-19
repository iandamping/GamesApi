package com.junemon.gamesapi.core.module

import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSource
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSourceImpl
import com.junemon.gamesapi.core.data.repository.GameRepositoryImpl
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.gamesapi.core.domain.usecase.GameUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.ScopeDSL

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

private fun ScopeDSL.bindRemoteDataSource() =
    scoped<GameRemoteDataSource> {
        GameRemoteDataSourceImpl(
            api = get(),
            defaultDispatcher = get(named("default"))
        )
    }

private fun ScopeDSL.bindCacheDataSource() =
    scoped<GameCacheDataSource> {
        GameCacheDataSourceImpl(
            get(),get()
        )
    }

private fun ScopeDSL.bindRepository() =
    scoped<GameRepository> {
        GameRepositoryImpl(
            remoteDataSource = get(),
            pagingRemoteDataSource = get(),
            defaultDispatcher = get(named("default")),
            cacheDataSource = get()
        )
    }

private fun ScopeDSL.provideGameUseCase() =
    scoped<GameUseCase> {
        GameUseCaseImpl(
            repository = get()
        )
    }

fun ScopeDSL.gameInjector() {
    bindRemoteDataSource()
    bindCacheDataSource()
    bindRepository()
    provideGameUseCase()
}
