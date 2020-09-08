package com.junemon.gamesapi.core.di.module

import android.content.Context
import com.junemon.gamesapi.MainApplication
import dagger.Module
import dagger.Provides


/**
 * Created by Ian Damping on 21,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
class ContextModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }
}