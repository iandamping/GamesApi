package com.junemon.gamesapi.util.adapter.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.util.inflates
import kotlinx.android.extensions.LayoutContainer

/**
 *
Created by Ian Damping on 08/06/2019.
Github = https://github.com/iandamping
 */
abstract class BaseListAdapter<T>(
    private val layout: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListAdapter.MyListWithSliderViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListWithSliderViewHolder {
        return MyListWithSliderViewHolder(
            parent.inflates(layout)
        )
    }

    class MyListWithSliderViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer
}
