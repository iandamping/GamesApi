package com.junemon.gamesapi.favorite.di

import com.junemon.gamesapi.favorite.feature.vm.GameFavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalCoroutinesApi
val favoriteModule = module {

    viewModel { GameFavoriteViewModel(get()) }

}

