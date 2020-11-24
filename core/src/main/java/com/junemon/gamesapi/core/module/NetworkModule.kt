package com.junemon.gamesapi.core.module

import com.google.gson.GsonBuilder
import com.junemon.gamesapi.core.data.datasource.remote.GamePaginationRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.network.ApiInterface
import okhttp3.CookieJar
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Ian Damping on 18,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
val remoteModule =
    helperNetworkModuleInjector<ApiInterface>(baseUrl = "https://api.rawg.io/api/")

val remotePaginationDataSourceModule = module {
    single { GamePaginationRemoteDataSource(get()) }
}

inline fun <reified T> helperNetworkModuleInjector(baseUrl: String) = module {
    single { createClient<T>(createOkHttpClient(), baseUrl) }
}

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .cookieJar(CookieJar.NO_COOKIES)
    .connectTimeout(15L, TimeUnit.SECONDS)
    .writeTimeout(15L, TimeUnit.SECONDS)
    .readTimeout(15L, TimeUnit.SECONDS)
    .dispatcher(Dispatcher().apply {
        maxRequests = 20
        maxRequestsPerHost = 20
    })
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .addInterceptor { chain ->
        chain.run { proceed(this.request().newBuilder().build()) }
    }.build()

inline fun <reified T> createClient(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(baseUrl)
        .build()
    return retrofit.create(T::class.java)
}
