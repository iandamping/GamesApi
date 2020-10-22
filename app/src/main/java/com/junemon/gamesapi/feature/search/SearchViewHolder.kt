package com.junemon.gamesapi.feature.search

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.databinding.ItemSearchBinding
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.data.model.GameSearch


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class SearchViewHolder(
    private val binding: ItemSearchBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GameSearch) {
        binding.run {
            loadImageHelper.loadWithGlide(ivItemGameSearchImage,data.backgroundImage)
            tvItemGameSearchName.text = data.name
        }
    }
}