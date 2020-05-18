package com.junemon.gamesapi.core.di.component

import com.junemon.gamesapi.core.cache.PreferenceHelper
import com.junemon.gamesapi.core.di.module.PresentationModule
import com.junemon.gamesapi.core.di.scope.PerActivities
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.feature.MainActivity
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import dagger.Component


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@PerActivities
@Component(modules = [PresentationModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {


    fun inject(activity: MainActivity)

    val provideRepository: GameRepository

    val providePreferenceHelper: PreferenceHelper


    @Component.Factory
    interface Factory {
        fun appComponent(appComponent: AppComponent): ActivityComponent
    }
}

interface ActivityComponentProvider {

    fun provideActivityComponent(): ActivityComponent
}