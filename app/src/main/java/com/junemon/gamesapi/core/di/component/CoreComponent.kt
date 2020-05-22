package com.junemon.gamesapi.core.di.component

import android.app.Application
import com.junemon.gamesapi.core.cache.PreferenceHelper
import com.junemon.gamesapi.core.di.module.*
import com.junemon.gamesapi.core.domain.repository.GameRepository
import dagger.BindsInstance
import dagger.Component


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Component(
    modules = [NetworkModule::class, CacheModule::class, DataModule::class,
        PresentationModule::class,DomainModule::class, CoroutineModule::class,ContextModule::class]
)
interface CoreComponent {

    val provideRepository: GameRepository

    @Component.Factory
    interface Factory {
        fun injectApplication(@BindsInstance application: Application): CoreComponent
    }
}

interface CoreComponentProvider {

    fun provideCoreComponent(): CoreComponent
}