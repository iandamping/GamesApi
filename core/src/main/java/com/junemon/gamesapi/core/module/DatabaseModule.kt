package com.junemon.gamesapi.core.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.junemon.gamesapi.core.data.datasource.cache.room.GameDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
private const val prefHelperInit = "Init preference"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), GameDatabase::class.java, "gaming_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<GameDatabase>().gameDao() }
}

val sharedPreferenceModule = module {
    single { provideSharedPreferences(androidContext()) }
}
private fun provideSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences(prefHelperInit, Context.MODE_PRIVATE)



