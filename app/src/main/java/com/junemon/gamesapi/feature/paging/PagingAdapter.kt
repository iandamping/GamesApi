package com.junemon.gamesapi.feature.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.junemon.gamesapi.databinding.ItemPagingBinding
import com.junemon.gamesapi.core.presentation.adapter.AdapterConstant.listGamePaginationAdapterCallback
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.data.model.GameData

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PagingAdapter(
    private val listener: PagingAdapterListener,
    private val loadImageHelper: LoadImageHelper
) :
    PagingDataAdapter<GameData, PagingViewHolder>(listGamePaginationAdapterCallback) {

    interface PagingAdapterListener {
        fun onClicked(data: GameData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            ItemPagingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            loadImageHelper
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                listener.onClicked(data)
            }
        }
    }
}