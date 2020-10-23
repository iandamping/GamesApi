package com.junemon.gamesapi.core.module

import com.junemon.gamesapi.core.data.data.datasource.DevelopersCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.DevelopersRemoteDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.data.data.repository.DevelopersRepositoryImpl
import com.junemon.gamesapi.core.data.data.repository.GameRepositoryImpl
import com.junemon.gamesapi.core.data.datasource.cache.DevelopersCacheDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.cache.GameCacheDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.remote.DevelopersRemoteDataSourceImpl
import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSourceImpl
import com.junemon.gamesapi.core.domain.repository.DevelopersRepository
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.domain.usecase.DevelopersUseCase
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.ScopeDSL

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

private fun ScopeDSL.bindRemoteDataSource() =
    scoped {
        GameRemoteDataSourceImpl(
            api = get(),
            defaultDispatcher = get(named("default"))
        ) as GameRemoteDataSource
    }

private fun ScopeDSL.bindCacheDataSource() =
    scoped {
        GameCacheDataSourceImpl(
            get(), get(), get(), get(), get()
        ) as GameCacheDataSource
    }

private fun ScopeDSL.bindDevelopersRemoteDataSource() =
    scoped {
        DevelopersRemoteDataSourceImpl(
            api = get(),
            defaultDispatcher = get(named("default"))
        ) as DevelopersRemoteDataSource
    }

private fun ScopeDSL.bindCacheDevelopersCacheDataSource() =
    scoped {
        DevelopersCacheDataSourceImpl(
            get()
        ) as DevelopersCacheDataSource
    }

private fun ScopeDSL.bindRepository() =
    scoped {
        GameRepositoryImpl(
            remoteDataSource = get(),
            pagingRemoteDataSource = get(),
            defaultDispatcher = get(named("default")),
            cacheDataSource = get()
        ) as GameRepository
    }

private fun ScopeDSL.bindDevelopersRepository() =
    scoped {
        DevelopersRepositoryImpl(
            remoteDataSource = get(),
            defaultDispatcher = get(named("default")),
            cacheDataSource = get()
        ) as DevelopersRepository
    }

private fun ScopeDSL.provideGameUseCase() =
    scoped {
        GameUseCase(
            repository = get()
        )
    }

private fun ScopeDSL.provideDevelopersUseCase() =
    scoped {
        DevelopersUseCase(
            repository = get()
        )
    }

fun ScopeDSL.gameInjector() {
    bindRemoteDataSource()
    bindCacheDataSource()
    bindRepository()
    provideGameUseCase()
}

fun ScopeDSL.developerInjector() {
    bindDevelopersRemoteDataSource()
    bindCacheDevelopersCacheDataSource()
    bindDevelopersRepository()
    provideDevelopersUseCase()
}