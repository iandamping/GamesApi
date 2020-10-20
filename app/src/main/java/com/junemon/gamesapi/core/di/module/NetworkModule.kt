package com.junemon.gamesapi.core.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.junemon.gamesapi.BuildConfig
import com.junemon.gamesapi.core.data.datasource.remote.GamePaginationRemoteDataSource
import com.junemon.gamesapi.core.network.ApiInterface
import com.junemon.gamesapi.util.AppConstant.baseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG){
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply {
                maxRequests = 20
                maxRequestsPerHost = 20
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                chain.run { proceed(this.request().newBuilder().build()) }
            }
        okHttpBuilder.build()
    }else{
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply {
                maxRequests = 20
                maxRequestsPerHost = 20
            })
            .addInterceptor { chain ->
                chain.run { proceed(this.request().newBuilder().build()) }
            }
        okHttpBuilder.build()
    }



    @Provides
    @JvmStatic
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(baseUrl)
            .build()
    }


    @Provides
    @JvmStatic
    fun provideApiInterface(): ApiInterface {
        return provideRetrofit().create(ApiInterface::class.java)
    }

    @Provides
    @JvmStatic
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @JvmStatic
    fun providePaginationDataSource(api:ApiInterface)= GamePaginationRemoteDataSource(api)


}