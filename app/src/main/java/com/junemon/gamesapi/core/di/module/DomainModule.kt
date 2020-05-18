package com.junemon.gamesapi.core.di.module

import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import dagger.Module
import dagger.Provides


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
object DomainModule {

    @Provides
    @JvmStatic
    fun provideGameUseCase(repository: GameRepository): GameUseCase {
        return GameUseCase(repository)
    }
}