package com.junemon.gamesapi.feature.paging

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.databinding.ItemPagingBinding
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PagingViewHolder(
    private val binding: ItemPagingBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Game) {
        binding.run {
            loadImageHelper.loadWithGlide(ivItemGameImage, data.gameImage)
            tvItemGameName.text = data.gameName
            tvItemGameGenre.text = data.gameGenre
        }
    }
}