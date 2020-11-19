package com.junemon.gamesapi.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.databinding.ItemSliderBinding
import com.junemon.gamesapi.util.adapter.AdapterConstant
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeSliderAdapter(
    private val listener: HomeSliderAdapterListener,
    private val loadImageHelper: LoadImageHelper
) :
    ListAdapter<Game, HomeSliderViewHolder>(AdapterConstant.listGameAdapterCallback) {

    interface HomeSliderAdapterListener {
        fun onClicked(data: Game)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        return HomeSliderViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            loadImageHelper
        )
    }

    override fun onBindViewHolder(holder: HomeSliderViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onClicked(data)
        }
    }

}