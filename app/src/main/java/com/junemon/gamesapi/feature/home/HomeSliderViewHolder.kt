package com.junemon.gamesapi.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junemon.gamesapi.databinding.ItemSliderBinding
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.model.games.GameData


/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeSliderViewHolder(
    private val binding: ItemSliderBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GameData) {
        binding.run {
            loadImageHelper.loadWithGlide(ivItemGameImage,data.backgroundImage)
            tvItemGameName.text = data.name
            tvItemGameGenre.text = data.genres?.get(0)?.name
        }
    }
}