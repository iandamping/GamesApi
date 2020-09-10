package com.junemon.gamesapi.feature.genre

import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.databinding.ItemGenreBinding
import com.junemon.model.games.GamesItem


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GenreViewHolder(private val binding:ItemGenreBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(data:GamesItem){
        binding.run {
            chipItemText.text = data.name
        }
    }
}