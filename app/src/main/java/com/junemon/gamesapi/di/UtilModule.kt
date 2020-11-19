package com.junemon.gamesapi.di

import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelperImpl
import org.koin.dsl.module

val utilModule = module {
    factory<LoadImageHelper> {
        LoadImageHelperImpl(
            get(),
            get()
        )
    }
}