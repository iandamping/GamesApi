package com.junemon.gamesapi.core.di.module

import com.junemon.gamesapi.core.di.scope.PerActivities
import com.junemon.gamesapi.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
abstract class ActivityBindingModule {


    @PerActivities
    @ContributesAndroidInjector(
        modules = [  // fragments
            GameModule::class]
    )
    abstract fun mainActivity(): MainActivity
}