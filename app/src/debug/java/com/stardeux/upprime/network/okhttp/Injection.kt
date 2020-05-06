package com.stardeux.upprime.network.okhttp

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.stardeux.upprime.network.di.networkModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val fullNetwork = networkModule.apply {
    single { provideOkHttp(get(), get(), get()) }
    factory { provideHttpLoggingInterceptor() }
}

fun provideOkHttp(
    okHttpClientBuilder: OkHttpClient.Builder,
    flipperOkhttpInterceptor: FlipperOkhttpInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return okHttpClientBuilder
        .addInterceptor(flipperOkhttpInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
}