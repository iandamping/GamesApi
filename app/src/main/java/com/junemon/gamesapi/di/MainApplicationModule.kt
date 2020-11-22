package com.junemon.gamesapi.di

import com.junemon.gamesapi.core.module.databaseModule
import com.junemon.gamesapi.core.module.dispatchersModule
import com.junemon.gamesapi.core.module.glideModule
import com.junemon.gamesapi.core.module.remoteModule
import com.junemon.gamesapi.core.module.remotePaginationDataSourceModule
import com.junemon.gamesapi.core.module.repositoryModule
import com.junemon.gamesapi.core.module.sharedPreferenceModule
import com.junemon.gamesapi.core.module.useCaseModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.core.context.loadKoinModules

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

@FlowPreview
@ExperimentalCoroutinesApi
fun injectData() = loadFeature

@FlowPreview
@ExperimentalCoroutinesApi
private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            databaseModule,
            sharedPreferenceModule,
            remotePaginationDataSourceModule,
            remoteModule,
            repositoryModule,
            useCaseModule,
            glideModule,
            utilModule,
            fragmentModule,
            dispatchersModule
        )
    )
}

