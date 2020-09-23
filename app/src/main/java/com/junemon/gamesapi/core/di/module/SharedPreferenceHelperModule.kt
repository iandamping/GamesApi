package com.junemon.gamesapi.core.di.module

import com.junemon.gamesapi.core.cache.preference.PreferenceHelper
import com.junemon.gamesapi.core.cache.preference.PreferenceHelperImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Ian Damping on 23,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
abstract class SharedPreferenceHelperModule {

    @Binds
    abstract fun bindSharedPreferenceHelper(sharedPreferences: PreferenceHelperImpl): PreferenceHelper
}