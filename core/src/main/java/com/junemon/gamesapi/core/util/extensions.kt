package com.junemon.gamesapi.core.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import java.util.*

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

inline var View.loadingVisibility: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (!value) View.VISIBLE else View.GONE
    }

fun ViewGroup.inflates(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

inline val Context.layoutInflater: LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun RecyclerView.horizontalRecyclerviewInitializer() {
    layoutManager = LinearLayoutManager(
        this.context, LinearLayoutManager.HORIZONTAL,
        false
    )
    if (this.onFlingListener == null) {
        RecyclerHorizontalSnapHelper()
            .attachToRecyclerView(this)
    }
}

fun RecyclerView.verticalRecyclerviewInitializer() {
    layoutManager = LinearLayoutManager(
        this.context, LinearLayoutManager.VERTICAL,
        false
    )
}

fun RecyclerView.gridRecyclerviewInitializer(size: Int) {
    layoutManager = GridLayoutManager(
        this.context, size
    )
}

fun generateRandomHexColor(): String {
    // create object of Random class
    val obj = Random()
    val randomNumber = obj.nextInt(0xffffff + 1)
    // format it as hexadecimal string and print

    return String.format("#%06x", randomNumber)
}

@FlowPreview
@ExperimentalCoroutinesApi
fun <T> SharedFlow<String>.search(searchData: (String) -> Flow<T>) = this.debounce(300)
    .distinctUntilChanged()
    .filter {
        it.trim().isNotEmpty()
    }.flatMapLatest {
        searchData.invoke(it)
    }.asLiveData()

