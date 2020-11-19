package com.junemon.gamesapi

import android.app.Application
import com.junemon.gamesapi.di.injectData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by Ian Damping on 02,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainApplication : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            injectData()

        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}