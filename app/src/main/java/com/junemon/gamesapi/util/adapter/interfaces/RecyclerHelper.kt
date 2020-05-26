package com.junemon.gamesapi.util.adapter.interfaces

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.util.adapter.base.BaseBindingKotlinListAdapter
import com.junemon.gamesapi.util.adapter.base.BaseKotlinListAdapter

interface RecyclerHelper {

    fun <T> RecyclerView.setUpVerticalListAdapter(
        items: List<T>?,
        diffUtil: DiffUtil.ItemCallback<T>,
        layoutResId: Int,
        bindHolder: View.(T) -> Unit,
        itemClick: T.() -> Unit = {},
        onLongClicks: T.() -> Unit = {},
        manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
    ): BaseKotlinListAdapter<T>?

    fun <T,B:ViewDataBinding> RecyclerView.setUpVerticalBindingListAdapter(
        items: List<T>?,
        diffUtil: DiffUtil.ItemCallback<T>,
        layoutResId: Int,
        itemClick: T.() -> Unit = {},
        onLongClicks: T.() -> Unit = {},
        manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
    ): BaseBindingKotlinListAdapter<T,B>?

}