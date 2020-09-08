package com.junemon.gamesapi.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.junemon.gamesapi.core.di.factory.ViewModelFactory
import com.junemon.gamesapi.util.adapter.classes.RecyclerHelperImpl
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelperImpl
import dagger.Binds
import dagger.Module


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
abstract class PresentationModule {


    @Binds
    abstract fun bindsRecyclerViewHelper(recyclerHelper: RecyclerHelperImpl): RecyclerHelper

    @Binds
    abstract fun bindsLoadImageHelper(loadImageHelper: LoadImageHelperImpl): LoadImageHelper
}