package com.junemon.gamesapi.core.di.component

import com.junemon.gamesapi.MainApplication
import com.junemon.gamesapi.core.cache.di.DatabaseHelperModule
import com.junemon.gamesapi.core.cache.di.DatabaseModule
import com.junemon.gamesapi.core.di.module.*
import com.junemon.gamesapi.core.di.scope.ApplicationScope
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.feature.MainActivity
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ContextModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        DatabaseHelperModule::class,
        CoroutineModule::class,
        DataModule::class,
        DomainModule::class,
        NetworkModule::class,
        PresentationModule::class,
        GlideModule::class]
)
interface AppComponent: AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}

