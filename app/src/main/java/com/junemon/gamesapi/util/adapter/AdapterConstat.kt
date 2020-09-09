package com.junemon.gamesapi.util.adapter

import androidx.recyclerview.widget.DiffUtil
import com.junemon.model.games.GameData

/**
 * Created by Ian Damping on 22,January,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
object AdapterConstant {

    val listGameAdapterCallback = object : DiffUtil.ItemCallback<GameData>() {
        override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}