package com.junemon.gamesapi.core.di

import android.app.Activity
import com.junemon.gamesapi.MainApplication


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun Activity.activityComponent() = (application as MainApplication).provideActivityComponent()