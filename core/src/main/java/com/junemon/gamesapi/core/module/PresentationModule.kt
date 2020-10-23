package com.junemon.gamesapi.core.module

import com.junemon.gamesapi.core.presentation.adapter.classes.RecyclerHelperImpl
import com.junemon.gamesapi.core.presentation.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelperImpl
import org.koin.dsl.module

/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

val presentationModule = module {
    single { RecyclerHelperImpl() as RecyclerHelper }
    single { LoadImageHelperImpl(get(),get()) as LoadImageHelper }
}
