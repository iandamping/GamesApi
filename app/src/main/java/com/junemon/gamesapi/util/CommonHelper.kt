package com.junemon.gamesapi.util

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.junemon.gamesapi.data_source.remote.GamesModel
import timber.log.Timber


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

inline fun <reified T> T.timberLogE(msg: String?) {
    val tag = T::class.java.simpleName
    Timber.tag("### Log di $tag ###").e(msg)
}

inline fun <reified T> T.timberLogD(msg: String?) {
    val tag = T::class.java.simpleName
    Timber.tag("### Log di $tag ###").d(msg)
}

inline fun <reified T> T.timberLogI(msg: String?) {
    val tag = T::class.java.simpleName
    Timber.tag("### Log di $tag ###").i(msg)
}

object CommonHelper {
    val gamesDiffCallbacks = object : DiffUtil.ItemCallback<GamesModel>() {
        override fun areItemsTheSame(oldItem: GamesModel, newItem: GamesModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GamesModel, newItem: GamesModel): Boolean =
            oldItem == newItem
    }
}

fun ImageView.loadWithGlide(url: String?) {
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    Glide.with(this.context).load(url).apply(requestOptions).thumbnail(0.25f).into(this)
}