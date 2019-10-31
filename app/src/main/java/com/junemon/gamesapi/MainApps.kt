package com.junemon.gamesapi

import android.app.Application
import androidx.multidex.MultiDex
import com.junemon.gamesapi.di.injectData
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainApps : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@MainApps)
            injectData()
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}