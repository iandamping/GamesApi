package com.junemon.gamesapi.util

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.Gson
import com.junemon.gamesapi.model.CreatorPresentation
import com.junemon.gamesapi.model.GamePresentation
import com.junemon.gamesapi.model.GamesItemPresentation
import com.junemon.gamesapi.model.PublisherPresentation

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

object CommonHelper {
    const val intentKey = "pass dataa"
    val gamesDiffCallbacks = object : DiffUtil.ItemCallback<GamePresentation>() {
        override fun areItemsTheSame(oldItem: GamePresentation, newItem: GamePresentation): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GamePresentation, newItem: GamePresentation): Boolean =
            oldItem == newItem
    }

    val publisherDiffCallbacks = object : DiffUtil.ItemCallback<PublisherPresentation>() {
        override fun areItemsTheSame(oldItem: PublisherPresentation, newItem: PublisherPresentation): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PublisherPresentation, newItem: PublisherPresentation): Boolean =
            oldItem == newItem
    }

    val creatorDiffCallbacks = object : DiffUtil.ItemCallback<CreatorPresentation>() {
        override fun areItemsTheSame(oldItem: CreatorPresentation, newItem: CreatorPresentation): Boolean =
            oldItem.results == newItem.results

        override fun areContentsTheSame(oldItem: CreatorPresentation, newItem: CreatorPresentation): Boolean =
            oldItem == newItem
    }

    val itemDiffCallbacks = object : DiffUtil.ItemCallback<GamesItemPresentation>() {
        override fun areItemsTheSame(oldItem: GamesItemPresentation, newItem: GamesItemPresentation): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GamesItemPresentation, newItem: GamesItemPresentation): Boolean =
            oldItem == newItem
    }
}
