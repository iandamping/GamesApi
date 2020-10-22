package com.junemon.gamesapi.util.adapter

import androidx.recyclerview.widget.DiffUtil
import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.core.data.model.GameData
import com.junemon.gamesapi.core.data.model.GameSearch
import com.junemon.gamesapi.core.data.model.GamesItem

/**
 * Created by Ian Damping on 22,January,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
object AdapterConstant {

    val listGameAdapterCallback = object : DiffUtil.ItemCallback<GameEntity>() {
        override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
            return oldItem.gameId == newItem.gameId
        }

        override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
            return oldItem == newItem
        }
    }

    val listGameGenreAdapterCallback = object : DiffUtil.ItemCallback<GamesItem>() {
        override fun areItemsTheSame(oldItem: GamesItem, newItem: GamesItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GamesItem, newItem: GamesItem): Boolean {
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

    val listGamePaginationAdapterCallback = object : DiffUtil.ItemCallback<GameData>() {
        override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean =
            oldItem == newItem
    }
}