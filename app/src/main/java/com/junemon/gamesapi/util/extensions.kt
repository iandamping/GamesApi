package com.junemon.gamesapi.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun ViewGroup.inflates(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

