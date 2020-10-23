package com.junemon.gamesapi.feature.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.databinding.ItemGenreBinding
import com.junemon.gamesapi.core.presentation.adapter.AdapterConstant
import com.junemon.gamesapi.core.data.model.GamesItem


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GenreRecycleAdapter(private val listener: GenreRecycleAdapterListener) :
    ListAdapter<GamesItem, GenreViewHolder>(AdapterConstant.listGameGenreAdapterCallback) {

    interface GenreRecycleAdapterListener {
        fun onClicked(data: GamesItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onClicked(data)
        }
    }
}