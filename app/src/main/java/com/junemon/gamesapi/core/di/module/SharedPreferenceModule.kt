package com.junemon.gamesapi.core.di.module

import android.content.Context
import android.content.SharedPreferences
import com.junemon.gamesapi.util.AppConstant
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Damping on 23,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
object SharedPreferenceModule {
    @Provides
    @JvmStatic
    fun provideSharedPreference(context: Context):SharedPreferences{
        return context.getSharedPreferences(AppConstant.prefHelperInit, Context.MODE_PRIVATE)
    }
}