package com.junemon.gamesapi.core.di.module

import androidx.lifecycle.ViewModel
import com.junemon.gamesapi.core.di.factory.ViewModelKey
import com.junemon.gamesapi.core.di.scope.FeatureScope
import com.junemon.gamesapi.feature.detail.DetailFragment
import com.junemon.gamesapi.feature.home.HomeFragment
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
abstract class GameModule {

    @FeatureScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @FeatureScope
    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindPlaceViewModel(gameViewModel: GameViewModel): ViewModel
}