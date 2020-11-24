package com.junemon.gamesapi.core.module

import android.content.Context
import com.google.gson.GsonBuilder
import com.junemon.gamesapi.core.data.datasource.remote.GamePaginationRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.network.ApiInterface
import com.junemon.gamesapi.core.data.datasource.remote.network.SSLCertificateConfigurator
import okhttp3.CookieJar
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.X509TrustManager

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
    single { createClient<T>(createOkHttpClient(androidContext()), baseUrl) }
}

fun provideTrustManager(context: Context): X509TrustManager {
    val trustManagerFactory = SSLCertificateConfigurator.getTrustManager(context)
    val trustManagers = trustManagerFactory.trustManagers
    if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
        throw IllegalStateException(
            "Unexpected default trust managers:" + Arrays.toString(
                trustManagers
            )
        )
    }
    return trustManagers[0] as X509TrustManager
}

fun createOkHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
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
    }.sslSocketFactory(
        SSLCertificateConfigurator.getSSLConfiguration(context).socketFactory,
        provideTrustManager(context)
    )
    .build()

inline fun <reified T> createClient(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(baseUrl)
        .build()
    return retrofit.create(T::class.java)
}
