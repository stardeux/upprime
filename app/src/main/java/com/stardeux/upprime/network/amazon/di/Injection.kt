package com.stardeux.upprime.network.amazon.di

import com.stardeux.upprime.network.amazon.AmazonApiConst
import com.stardeux.upprime.network.amazon.AmazonAuthenticatorInterceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val amazonNetworkModule = module {
    factory { provideAmazonAuthenticatorInterceptor() }
    factory(named(AMAZON_NAMED_QUALIFIER)) { provideAmazonOkHttpBuilder(get()) }
    single(named(AMAZON_NAMED_QUALIFIER)) { provideAmazonRetrofit(get((named(AMAZON_NAMED_QUALIFIER)))) }
}

fun provideAmazonAuthenticatorInterceptor(): AmazonAuthenticatorInterceptor {
    return AmazonAuthenticatorInterceptor()
}

fun provideAmazonRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient).baseUrl(AmazonApiConst.API_BASE_URL).build()
}

fun provideAmazonOkHttpBuilder(
    amazonAuthenticatorInterceptor: AmazonAuthenticatorInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(amazonAuthenticatorInterceptor)
    }
}

const val AMAZON_NAMED_QUALIFIER = "amazon"