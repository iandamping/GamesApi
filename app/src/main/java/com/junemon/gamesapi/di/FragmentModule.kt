package com.junemon.gamesapi.di

import com.junemon.gamesapi.feature.detail.DetailFragment
import com.junemon.gamesapi.feature.home.HomeFragment
import com.junemon.gamesapi.feature.paging.PagingFragment
import com.junemon.gamesapi.feature.search.SearchFragment
import org.koin.dsl.module

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
val fragmentModule = module {

    scope<HomeFragment> {
        gameViewModelInjector()
    }

    scope<SearchFragment> {
        gameViewModelInjector()
    }
    scope<PagingFragment> {
        gameViewModelInjector()
    }
    scope<DetailFragment> {
        gameViewModelInjector()
    }
}