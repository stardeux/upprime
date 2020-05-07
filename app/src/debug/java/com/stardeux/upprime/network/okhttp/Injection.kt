package com.stardeux.upprime.network.okhttp

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.network.amazon.di.amazonNetworkModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named

val fullNetwork = amazonNetworkModule.apply {
    single(named(AMAZON_NAMED_QUALIFIER)) { provideOkHttp(get((named(AMAZON_NAMED_QUALIFIER))), get(), get()) }
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