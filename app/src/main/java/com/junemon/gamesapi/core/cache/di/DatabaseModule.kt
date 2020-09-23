package com.junemon.gamesapi.core.cache.di

import android.content.Context
import androidx.room.Room
import com.junemon.gamesapi.core.cache.dao.GameDao
import com.junemon.gamesapi.core.cache.db.GameDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Module
object DatabaseModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDb(app: Context): GameDatabase {
        return Room
            .databaseBuilder(app, GameDatabase::class.java, "gaming_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @JvmStatic
    fun provideGameDao(db: GameDatabase): GameDao {
        return db.gameDao()
    }
}