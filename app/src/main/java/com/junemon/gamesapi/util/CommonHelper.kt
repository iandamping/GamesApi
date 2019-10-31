package com.junemon.gamesapi.util

import androidx.recyclerview.widget.DiffUtil
import com.junemon.gamesapi.model.GameItem

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

object CommonHelper {
    val gamesDiffCallbacks = object : DiffUtil.ItemCallback<GameItem>() {
        override fun areItemsTheSame(oldItem: GameItem, newItem: GameItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean =
            oldItem == newItem
    }
}
