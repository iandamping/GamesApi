package com.junemon.gamesapi.util.imageHelper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.junemon.gamesapi.R
import com.junemon.gamesapi.core.di.module.IoDispatcher
import com.junemon.gamesapi.core.di.module.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoadImageHelperImpl @Inject constructor(
    private val requestManager: RequestManager,
    private val requestOptions: RequestOptions
) : LoadImageHelper {

    override fun ImageView.loadWithGlide(url: String?) {
        requestManager
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .placeholder(R.drawable.empty_image).thumbnail(0.25f).into(this)
    }


}