package com.stardeux.upprime.network.okhttp

import com.stardeux.upprime.network.di.networkModule
import okhttp3.OkHttpClient

val fullNetwork = networkModule.apply {
    single { provideOkHttp(get()) }
}

fun provideOkHttp(
    okHttpClientBuilder: OkHttpClient.Builder
): OkHttpClient {
    return okHttpClientBuilder.build()
}