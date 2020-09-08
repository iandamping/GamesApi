package com.junemon.gamesapi

import com.junemon.gamesapi.core.di.component.*
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Created by Ian Damping on 02,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainApplication: DaggerApplication()  {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }


}