package com.junemon.gamesapi.core.cache.di

import com.junemon.gamesapi.core.cache.util.GameDaoHelper
import com.junemon.gamesapi.core.cache.util.GameDaoHelperImpl
import dagger.Binds
import dagger.Module


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
abstract class DatabaseHelperModule {

    @Binds
    abstract fun bindsGameDaoHelper(gameDaoHelper: GameDaoHelperImpl): GameDaoHelper
}