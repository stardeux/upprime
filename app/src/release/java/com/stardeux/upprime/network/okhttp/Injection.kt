package com.stardeux.upprime.network.okhttp

import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.network.amazon.di.amazonNetworkModule
import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.network.tmdb.di.tmdbNetworkModule
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named

val fullAmazonNetwork = amazonNetworkModule.apply {
    single(named(AMAZON_NAMED_QUALIFIER)) { provideOkHttp(get(named(AMAZON_NAMED_QUALIFIER))) }
}

val fullTmdbNetwork = tmdbNetworkModule.apply {
    single(named(TMDB_NAMED_QUALIFIER)) { provideOkHttp(get(named(TMDB_NAMED_QUALIFIER))) }
}

fun provideOkHttp(
    okHttpClientBuilder: OkHttpClient.Builder
): OkHttpClient {
    return okHttpClientBuilder.build()
}