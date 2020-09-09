package com.junemon.gamesapi.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.databinding.ItemSliderBinding
import com.junemon.gamesapi.util.adapter.AdapterConstant
import com.junemon.model.games.GamesModel


/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeSliderAdapter(private val listener: HomeSliderAdapterListener) :
    ListAdapter<GamesModel, HomeSliderViewHolder>(AdapterConstant.listGameAdapterCallback) {

    interface HomeSliderAdapterListener {
        fun onClicked(data: GamesModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        return HomeSliderViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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