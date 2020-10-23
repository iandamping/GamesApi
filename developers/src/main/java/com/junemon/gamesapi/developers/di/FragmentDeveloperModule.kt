package com.junemon.gamesapi.developers.di

import com.junemon.gamesapi.developers.feature.DevelopersFragment
import com.junemon.gamesapi.di.MainApplicationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

@ExperimentalCoroutinesApi
private val loadFeature by lazy { loadKoinModules(injectDynamicFeatureModule()) }

@ExperimentalCoroutinesApi
fun injectData() = loadFeature

@ExperimentalCoroutinesApi
private fun injectDynamicFeatureModule(): MutableList<Module> {
    MainApplicationModule.initDynamicFeatureModule(fragmentDeveloperModule)
    return MainApplicationModule.listOfModule
}

private val fragmentDeveloperModule = module {
    scope<DevelopersFragment> {
        developerViewModelInjector()
    }
}