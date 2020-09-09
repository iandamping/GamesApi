package com.junemon.gamesapi.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junemon.gamesapi.databinding.ItemSliderBinding
import com.junemon.model.games.GamesModel


/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeSliderViewHolder(private val binding:ItemSliderBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(data:GamesModel){
        binding.run {
            Glide.with(ivItemGameImage).load(data.backgroundImage)
                .into(ivItemGameImage)
            tvItemGameName.text = data.name
            tvItemGameGenre.text = data.genres?.get(0)?.name
        }
    }
}