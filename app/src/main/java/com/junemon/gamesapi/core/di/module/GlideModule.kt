package com.junemon.gamesapi.core.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.junemon.gamesapi.R
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Damping on 23,June,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
object GlideModule {

    @Provides
    @JvmStatic
    fun provideGlide(context: Context): Glide {
        return Glide.get(context)
    }

    @Provides
    @JvmStatic
    fun provideGlideRequestManager(context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @JvmStatic
    fun provideGlideRequestOptions(): RequestOptions {
        return RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.no_data)
//            .placeholder(R.drawable.empty_image)
    }
}