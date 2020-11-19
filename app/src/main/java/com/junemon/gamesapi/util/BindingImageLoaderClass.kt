package com.junemon.gamesapi.util

import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.junemon.gamesapi.core.R

/**
 * Created by Ian Damping on 25,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
object BindingImageLoaderClass {
    private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    @JvmStatic
    @BindingAdapter("loadImageHelper")
    fun loadImageHelper(view: ImageView, url: String?) {
        if (url != null) {
            Glide.with(view)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(requestOptions)
                .error(R.drawable.empty_image).thumbnail(0.25f).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("initBookmark")
    fun initBookmarkHelper(view: ImageButton, state: Boolean) {
        if (state) {
            view.setImageResource(R.drawable.ic_bookmarked)
        } else view.setImageResource(R.drawable.ic_unbookmark)
    }
}