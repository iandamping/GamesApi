package com.junemon.gamesapi.di

import com.junemon.gamesapi.core.module.databaseHelperModule
import com.junemon.gamesapi.core.module.databaseModule
import com.junemon.gamesapi.core.module.dispatchersModule
import com.junemon.gamesapi.core.module.glideModule
import com.junemon.gamesapi.core.module.presentationModule
import com.junemon.gamesapi.core.module.remoteModule
import com.junemon.gamesapi.core.module.remotePaginationDataSourceModule
import com.junemon.gamesapi.core.module.sharedPreferenceHelperModule
import com.junemon.gamesapi.core.module.sharedPreferenceListenerModule
import com.junemon.gamesapi.core.module.sharedPreferenceModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
// @ExperimentalCoroutinesApi
// fun injectData() = loadFeature
//
// @ExperimentalCoroutinesApi
// private val loadFeature by lazy {
//     loadKoinModules(
//         listOf(
//             databaseModule,
//             databaseHelperModule,
//             sharedPreferenceModule,
//             sharedPreferenceHelperModule,
//             sharedPreferenceListenerModule,
//             remotePaginationDataSourceModule,
//             remoteModule,
//             glideModule,
//             presentationModule,
//             fragmentModule,
//             dispatchersModule
//         )
//     )
// }



private val loadFeature by lazy { loadKoinModules(MainApplicationModule.listOfModule) }

@ExperimentalCoroutinesApi
fun injectData() = loadFeature


object MainApplicationModule {
    private var _listOfModule: MutableList<Module> = mutableListOf()

    val listOfModule = _listOfModule

    init {
        _listOfModule.addAll(listOf(
            databaseModule,
            databaseHelperModule,
            sharedPreferenceModule,
            sharedPreferenceHelperModule,
            sharedPreferenceListenerModule,
            remotePaginationDataSourceModule,
            remoteModule,
            glideModule,
            presentationModule,
            fragmentModule,
            dispatchersModule
        ))
    }

    fun initDynamicFeatureModule(module: Module) {
        _listOfModule.add(module)
    }
}
