package com.junemon.gamesapi.favorite.feature.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.favorite.databinding.ItemFavoriteBinding
import com.junemon.gamesapi.favorite.util.FavoriteAdapterConstant
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 20,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class FavoriteAdapter(
    private val listener: FavoriteAdapterListener,
    private val loadImageHelper: LoadImageHelper
) : ListAdapter<GameFavorite, FavoriteViewHolder>(FavoriteAdapterConstant.listFavoriteGameAdapterCallback) {

    interface FavoriteAdapterListener {
        fun onClicked(data: GameFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            loadImageHelper
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onClicked(data)
        }
    }
}