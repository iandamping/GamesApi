package com.junemon.gamesapi.util.imageHelper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.junemon.gamesapi.R

class LoadImageHelperImpl: LoadImageHelper {

    override fun ImageView.loadWithGlide(url: String?) {
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        Glide.with(this.context).load(url).apply(requestOptions).placeholder(R.drawable.empty_image).thumbnail(0.25f).into(this)
    }


}