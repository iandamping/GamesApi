package com.junemon.gamesapi.di

import com.junemon.gamesapi.feature.detail.DetailFragment
import com.junemon.gamesapi.feature.home.HomeFragment
import com.junemon.gamesapi.feature.paging.PagingFragment
import com.junemon.gamesapi.feature.search.SearchFragment
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalCoroutinesApi
val fragmentModule = module {

    scope<HomeFragment> {
        viewModel { GameViewModel(get()) }
    }

    scope<SearchFragment> {
        viewModel { GameViewModel(get()) }
    }
    scope<PagingFragment> {
        viewModel { GameViewModel(get()) }
    }
    scope<DetailFragment> {
        viewModel { GameViewModel(get()) }
    }
}