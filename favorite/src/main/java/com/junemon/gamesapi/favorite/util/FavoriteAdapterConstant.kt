package com.junemon.gamesapi.favorite.util

import androidx.recyclerview.widget.DiffUtil
import com.junemon.gamesapi.core.domain.model.GameFavorite

/**
 * Created by Ian Damping on 20,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
object FavoriteAdapterConstant {
    val listFavoriteGameAdapterCallback = object : DiffUtil.ItemCallback<GameFavorite>() {
        override fun areItemsTheSame(oldItem: GameFavorite, newItem: GameFavorite): Boolean {
            return oldItem.gameFavoriteId == newItem.gameFavoriteId
        }

        override fun areContentsTheSame(oldItem: GameFavorite, newItem: GameFavorite): Boolean {
            return oldItem == newItem
        }
    }
}