package com.junemon.gamesapi.feature.footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.junemon.gamesapi.databinding.AdaperFooterLoadingBinding

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class FooterLoadingAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterLoadingViewHolder>() {

    override fun onBindViewHolder(holder: FooterLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FooterLoadingViewHolder {
        return FooterLoadingViewHolder(
            AdaperFooterLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry = retry
        )
    }
}