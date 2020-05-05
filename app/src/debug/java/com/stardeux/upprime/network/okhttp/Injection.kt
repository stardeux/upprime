package com.stardeux.upprime.network.okhttp

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.stardeux.upprime.network.di.networkModule
import okhttp3.OkHttpClient

val fullNetwork = networkModule.apply {
    single { provideOkHttp(get(), get()) }
}

fun provideOkHttp(
    okHttpClientBuilder: OkHttpClient.Builder,
    flipperOkhttpInterceptor: FlipperOkhttpInterceptor
): OkHttpClient {
    return okHttpClientBuilder
        .addInterceptor(flipperOkhttpInterceptor)
        .build()
}