package com.junemon.gamesapi.util.adapter

import androidx.recyclerview.widget.DiffUtil
import com.junemon.gamesapi.core.model.GamesModel

/**
 * Created by Ian Damping on 22,January,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
object AdapterConstant {

    val listGameAdapterCallback = object : DiffUtil.ItemCallback<GamesModel>() {
        override fun areItemsTheSame(oldItem: GamesModel, newItem: GamesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GamesModel, newItem: GamesModel): Boolean {
            return oldItem == newItem
        }
    }
}