package com.junemon.gamesapi.util.adapter.classes

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.util.adapter.base.BaseKotlinListAdapter
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import javax.inject.Inject

/**
 *
Created by Ian Damping on 30/07/2019.
Github = https://github.com/iandamping

This class has been customized to return adapter position
 */
class RecyclerHelperImpl @Inject constructor() : RecyclerHelper {

    override fun <T> RecyclerView.setUpVerticalListAdapter(
        items: List<T>?,
        diffUtil: DiffUtil.ItemCallback<T>,
        layoutResId: Int,
        bindHolder: View.(T) -> Unit,
        itemClick: T.() -> Unit,
        onLongClicks: T.() -> Unit,
        manager: RecyclerView.LayoutManager
    ): BaseKotlinListAdapter<T>? {
        requireNotNull(items) {
            " your list data is null"
        }

        return BaseKotlinListAdapter(
            layout = layoutResId,
            bindHolder = {
                bindHolder(it)
            },
            diffUtil = diffUtil,
            onLongClickListener = { onLongClicks() },
            itemClicks = { itemClick() }).apply {
            layoutManager = manager
            adapter = this
            submitList(items)
            notifyDataSetChanged()
        }
    }


}
