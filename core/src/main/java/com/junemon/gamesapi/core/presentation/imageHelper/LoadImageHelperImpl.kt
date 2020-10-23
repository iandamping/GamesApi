package com.junemon.gamesapi.core.presentation.imageHelper

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class LoadImageHelperImpl(
    private val requestManager: RequestManager,
    private val requestOptions: RequestOptions
) : LoadImageHelper {

    override fun loadWithGlide(view:ImageView, url: String?) {
        requestManager
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .thumbnail(0.25f).into(view)
    }


}