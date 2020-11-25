package com.junemon.gamesapi.util.adapter

import androidx.recyclerview.widget.DiffUtil
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameSearch

/**
 * Created by Ian Damping on 22,January,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
object AdapterConstant {

    val listGameAdapterCallback = object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameGenre == newItem.gameGenre
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }

    val listGameSearchAdapterCallback = object : DiffUtil.ItemCallback<GameSearch>() {
        override fun areItemsTheSame(oldItem: GameSearch, newItem: GameSearch): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameSearch, newItem: GameSearch): Boolean {
            return oldItem == newItem
        }
    }

    val listGamePaginationAdapterCallback = object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean =
            oldItem.gameId == newItem.gameId

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
            oldItem == newItem
    }
}