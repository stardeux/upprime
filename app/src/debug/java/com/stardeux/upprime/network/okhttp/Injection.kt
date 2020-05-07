package com.stardeux.upprime.network.okhttp

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.network.amazon.di.amazonNetworkModule
import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.network.tmdb.di.tmdbNetworkModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonNetwork = module {
    factory { provideHttpLoggingInterceptor() }
}

val fullAmazonNetwork = amazonNetworkModule.apply {
    single(named(AMAZON_NAMED_QUALIFIER)) { provideOkHttp(get(named(AMAZON_NAMED_QUALIFIER)), get(), get()) }
}

val fullTmdbNetwork = tmdbNetworkModule.apply {
    single(named(TMDB_NAMED_QUALIFIER)) { provideOkHttp(get(named(TMDB_NAMED_QUALIFIER)), get(), get()) }
}

fun provideOkHttp(
    okHttpClientBuilder: OkHttpClient.Builder,
    flipperOkHttpInterceptor: FlipperOkhttpInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return okHttpClientBuilder
        .addInterceptor(flipperOkHttpInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
}