package com.junemon.gamesapi.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.databinding.ItemSliderBinding
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeSliderViewHolder(
    private val binding: ItemSliderBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GameEntity) {
        binding.run {
            loadImageHelper.loadWithGlide(ivItemGameImage,data.gameImage)
            tvItemGameName.text = data.gameName
            tvItemGameGenre.text = data.gameGenre
        }
    }
}