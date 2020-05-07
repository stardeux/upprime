package com.stardeux.upprime.network.okhttp

import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.network.amazon.di.amazonNetworkModule
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonNetwork = module { }

val fullAmazonNetwork = amazonNetworkModule.apply {
    single(named(AMAZON_NAMED_QUALIFIER)) { provideOkHttp(get(named(AMAZON_NAMED_QUALIFIER))) }
}

fun provideOkHttp(
    okHttpClientBuilder: OkHttpClient.Builder
): OkHttpClient {
    return okHttpClientBuilder.build()
}