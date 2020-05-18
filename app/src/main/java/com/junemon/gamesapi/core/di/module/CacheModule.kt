package com.junemon.gamesapi.core.di.module

import android.app.Application
import com.junemon.gamesapi.core.cache.PreferenceHelper
import dagger.Module
import dagger.Provides


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
object CacheModule {

    @Provides
    @JvmStatic
    fun providePreferenceHelper(app: Application): PreferenceHelper {
        return PreferenceHelper(app)
    }
}