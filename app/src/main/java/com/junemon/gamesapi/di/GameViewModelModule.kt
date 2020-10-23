package com.junemon.gamesapi.di

import com.junemon.gamesapi.core.module.gameInjector
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.ScopeDSL

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalCoroutinesApi
fun ScopeDSL.gameViewModelInjector() {
    gameInjector()
    viewModel { GameViewModel(get()) }
}