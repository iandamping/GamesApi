package com.junemon.gamesapi.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.databinding.ItemSliderBinding
import com.junemon.gamesapi.util.adapter.AdapterConstant
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.model.games.GameData


/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeSliderAdapter(
    private val listener: HomeSliderAdapterListener,
    private val loadImageHelper: LoadImageHelper
) :
    ListAdapter<GameData, HomeSliderViewHolder>(AdapterConstant.listGameAdapterCallback) {

    interface HomeSliderAdapterListener {
        fun onClicked(data: GameData)
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