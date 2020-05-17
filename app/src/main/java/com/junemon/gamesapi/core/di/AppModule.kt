package com.junemon.gamesapi.core.di

import com.junemon.gamesapi.core.cache.PreferenceHelper
import com.junemon.gamesapi.core.network.ApiInterface
import com.junemon.gamesapi.core.network.helperNetworkModule
import com.junemon.gamesapi.util.AppConstant.baseUrl
import com.junemon.gamesapi.util.adapter.classes.RecyclerHelperImpl
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelperImpl
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun injectModules() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            recyclerviewModule,
            glideModule,
            networkModule,
            preferenceHelpModule,
            dataSourceModule,
            repositoryModule,
            useCaseModule
        )
    )
}

private val networkModule =
    helperNetworkModule<ApiInterface>(
        baseUrl
    )

private val preferenceHelpModule = module {
    single {
        PreferenceHelper(
            get()
        )
    }
}

private val recyclerviewModule = module {
    factory { RecyclerHelperImpl() as RecyclerHelper }
}

private val glideModule = module {
    factory { LoadImageHelperImpl() as LoadImageHelper }
}
