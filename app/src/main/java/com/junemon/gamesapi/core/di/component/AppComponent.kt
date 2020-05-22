package com.junemon.gamesapi.core.di.component

import com.junemon.gamesapi.core.di.scope.ApplicationScope
import com.junemon.gamesapi.core.domain.repository.GameRepository
import dagger.Component


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ApplicationScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    val provideRepository: GameRepository

    @Component.Factory
    interface Factory {
        fun coreComponent(coreComponent: CoreComponent): AppComponent
    }
}

interface AppComponentProvider {

    fun provideAppComponent(): AppComponent
}