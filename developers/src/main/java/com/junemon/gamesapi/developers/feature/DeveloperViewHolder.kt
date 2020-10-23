package com.junemon.gamesapi.developers.feature

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelper
import com.junemon.gamesapi.developers.databinding.ItemDeveloperBinding

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DeveloperViewHolder(
    private val binding: ItemDeveloperBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: DeveloperData) {
        binding.run {
            loadImageHelper.loadWithGlide(ivItemDeveloperImage, data.imageBackground)
            tvItemDeveloperGenre.text = data.slug
            tvItemDeveloperName.text = data.name
        }
    }
}