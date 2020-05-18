package com.junemon.gamesapi

import android.app.Application
import com.junemon.gamesapi.core.di.component.*
import timber.log.Timber

/**
 * Created by Ian Damping on 02,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainApplication : Application(), ActivityComponentProvider, AppComponentProvider,
    CoreComponentProvider {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun provideActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.factory().appComponent(provideAppComponent())
    }

    override fun provideAppComponent(): AppComponent {
        return DaggerAppComponent.factory().coreComponent(provideCoreComponent())
    }

    override fun provideCoreComponent(): CoreComponent {
        return DaggerCoreComponent.factory().injectApplication(this)
    }


}