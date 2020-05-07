package com.stardeux.upprime.network.amazon.di

import com.stardeux.upprime.network.amazon.AmazonApiConst
import com.stardeux.upprime.network.amazon.AmazonAuthenticatorInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val amazonNetworkModule = module {
    factory { provideAmazonAuthenticatorInterceptor() }
    single { provideAmazonOkHttpBuilder(get()) }
    single { provideAmazonRetrofit(get()) }
}

fun provideAmazonAuthenticatorInterceptor(): AmazonAuthenticatorInterceptor {
    return AmazonAuthenticatorInterceptor()
}

fun provideAmazonRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(AmazonApiConst.API_BASE_URL)
        .build()
}

fun provideAmazonOkHttpBuilder(
    amazonAuthenticatorInterceptor: AmazonAuthenticatorInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(amazonAuthenticatorInterceptor)
    }
}