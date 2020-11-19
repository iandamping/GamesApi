package com.junemon.gamesapi.feature.paging

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.databinding.ItemPagingBinding
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.domain.model.GameData

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PagingViewHolder(
    private val binding: ItemPagingBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GameData) {
        binding.run {
            loadImageHelper.loadWithGlide(ivItemGameImage, data.backgroundImage)
            tvItemGameName.text = data.name
            tvItemGameGenre.text = data.genres?.get(0)?.name
        }
    }
}