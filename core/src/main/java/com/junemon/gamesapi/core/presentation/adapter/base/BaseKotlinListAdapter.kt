package com.junemon.gamesapi.core.presentation.adapter.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil

/**
 *
Created by Ian Damping on 30/07/2019.
Github = https://github.com/iandamping

This class has been customized to return adapter position
 */
class BaseKotlinListAdapter<T>(
    layout: Int,
    private val bindHolder: View.(T) -> Unit,
    diffUtil: DiffUtil.ItemCallback<T>,
    private val itemClicks: T.() -> Unit,
    private val onLongClickListener: T.() -> Unit = {}
) : BaseListAdapter<T>(layout, diffUtil) {

    override fun onBindViewHolder(holder: MyListWithSliderViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.bindHolder(data)
        holder.itemView.setOnClickListener { itemClicks(data) }
        holder.itemView.setOnLongClickListener {
            onLongClickListener(data)
            return@setOnLongClickListener true
        }
    }
}
