package com.junemon.gamesapi.di

import com.ian.app.helper.di.helperNetworkModule
import com.junemon.gamesapi.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.data.data.repository.GamesRepositoryImpl
import com.junemon.gamesapi.data.datasource.cache.GameCacheDataSourceImpl
import com.junemon.gamesapi.data.datasource.remote.ApiConstant
import com.junemon.gamesapi.data.datasource.remote.GamesApi
import com.junemon.gamesapi.data.datasource.remote.GamesRemoteDataSourceImpl
import com.junemon.gamesapi.data.db.databaseModule
import com.junemon.gamesapi.ui.GameDataViewModel
import com.junemon.gamesapi.domain2.repository.GamesRepository
import com.junemon.gamesapi.domain2.usecase.GamesUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun injectData() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            networkModule, dataSourceModule, repositoryModule, useCaseModule, viewmodelModule,databaseModule
        )
    )
}

private val viewmodelModule = module {
    viewModel { GameDataViewModel(get()) }
}

private val useCaseModule = module {
    factory { GamesUseCase(get()) }
}

private val networkModule = helperNetworkModule<GamesApi>(ApiConstant.baseUrl)

private val dataSourceModule = module {
    single { GamesRemoteDataSourceImpl(get()) as GamesRemoteDataSource }
    single { GameCacheDataSourceImpl(get()) as GameCacheDataSource }
}

private val repositoryModule = module {
    single { GamesRepositoryImpl(remoteSource = get(),localSource = get()) as GamesRepository }
}

