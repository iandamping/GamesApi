package com.junemon.gamesapi.core.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.junemon.gamesapi.core.cache.db.GameDatabase
import com.junemon.gamesapi.core.cache.preference.PreferenceHelper
import com.junemon.gamesapi.core.cache.preference.PreferenceHelperImpl
import com.junemon.gamesapi.core.cache.preference.listener.BooleanPrefValueListener
import com.junemon.gamesapi.core.cache.preference.listener.IntPrefValueListener
import com.junemon.gamesapi.core.cache.preference.listener.StringPrefValueListener
import com.junemon.gamesapi.core.cache.util.GameDaoHelper
import com.junemon.gamesapi.core.cache.util.GameDaoHelperImpl
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


val databaseHelperModule = module {
    single { GameDaoHelperImpl(get()) as GameDaoHelper }
}

val sharedPreferenceHelperModule = module {
    single { PreferenceHelperImpl(get()) as PreferenceHelper }
}

val sharedPreferenceListenerModule = module {
    single { StringPrefValueListener()  }
    single { BooleanPrefValueListener()  }
    single { IntPrefValueListener()  }
}



