package com.junemon.gamesapi.developers.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.gamesapi.core.presentation.adapter.AdapterConstant
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelper
import com.junemon.gamesapi.developers.databinding.ItemDeveloperBinding

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DeveloperAdapter(
    private val listener: DeveloperAdapterListener,
    private val loadImageHelper: LoadImageHelper,
) : ListAdapter<DeveloperData, DeveloperViewHolder>(AdapterConstant.listDeveloperAdapterCallback) {

    interface DeveloperAdapterListener {
        fun onClicked(data: DeveloperData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        return DeveloperViewHolder(
            ItemDeveloperBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            loadImageHelper
        )
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onClicked(data)
        }
    }
}