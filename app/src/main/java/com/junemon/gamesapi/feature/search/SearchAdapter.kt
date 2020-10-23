package com.junemon.gamesapi.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.databinding.ItemSearchBinding
import com.junemon.gamesapi.core.presentation.adapter.AdapterConstant
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.data.model.GameSearch


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class SearchAdapter(
    private val listener: SearchAdapterListener,
    private val loadImageHelper: LoadImageHelper
) :
    ListAdapter<GameSearch, SearchViewHolder>(AdapterConstant.listGameSearchAdapterCallback) {

    interface SearchAdapterListener {
        fun onClicked(data: GameSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            loadImageHelper
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onClicked(data)
        }
    }
}