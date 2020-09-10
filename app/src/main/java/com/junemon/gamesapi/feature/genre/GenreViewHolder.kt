package com.junemon.gamesapi.feature.genre

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.junemon.gamesapi.databinding.ItemGenreBinding
import com.junemon.gamesapi.util.generateRandomHexColor
import com.junemon.model.games.GamesItem


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GenreViewHolder(private val binding: ItemGenreBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GamesItem) {
        binding.run {
            val textLong = data.name.length

            val texts:String = when{
                textLong >= 14 ->{
                    data.name.subSequence(0, 14).toString() + "..."
                }
                else ->{
                    data.name
                }
            }
            chipItemText.chipBackgroundColor =
                ColorStateList.valueOf(Color.parseColor(generateRandomHexColor()))
            chipItemText.text = texts
        }
    }

}