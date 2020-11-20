package com.junemon.gamesapi.favorite.feature.favorite

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.favorite.databinding.ItemFavoriteBinding
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 20,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class FavoriteViewHolder(
    private val binding: ItemFavoriteBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GameFavorite) {
        binding.run {
            loadImageHelper.loadWithGlide(ivImages, data.backgroundImage)
            tvText.text = data.name
        }
    }
}