package com.junemon.gamesapi.di

import com.google.gson.GsonBuilder
import com.junemon.gamesapi.api.ApiConstant.baseUrl
import com.junemon.gamesapi.api.ApiInterface
import com.junemon.gamesapi.data_source.remote.GamesDataRepository
import com.junemon.gamesapi.data_source.remote.GamesDataSource
import com.junemon.gamesapi.data_source.ui.GameDataViewModel
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */


fun injectData() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            networkModule, dataSourceModule, repositoryModule, viewmodelModule
        )
    )
}
private val networkModule = module {
    single { createOkHttpClient() }
    single { createClient<ApiInterface>(get()) }
}

private val dataSourceModule = module {
    single { GamesDataSource(get()) }
}

private val repositoryModule = module {
    single { GamesDataRepository(get()) }
}

private val viewmodelModule = module {
    viewModel { GameDataViewModel(get()) }
}

private fun createOkHttpClient(): OkHttpClient {
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
            chain.run {
                proceed(this.request().newBuilder().build())
            }
        }
    return okHttpBuilder.build()
}

private inline fun <reified T> createClient(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(baseUrl)
        .build()
    return retrofit.create(T::class.java)
}

