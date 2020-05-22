package com.stardeux.upprime.network.amazon.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.network.amazon.AmazonApiConst
import com.stardeux.upprime.network.amazon.AmazonAuthenticatorInterceptor
import com.stardeux.upprime.network.amazon.LocalDateTimeJsonDeserializer
import com.stardeux.upprime.network.amazon.MediaTypeJsonDeserializer
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val amazonNetworkModule = module {
    factory { provideAmazonAuthenticatorInterceptor() }
    factory(named(AMAZON_NAMED_QUALIFIER)) { provideGson() }
    factory(named(AMAZON_NAMED_QUALIFIER)) { provideAmazonOkHttpBuilder(get()) }
    single(named(AMAZON_NAMED_QUALIFIER)) {
        provideAmazonRetrofit(
            get(named(AMAZON_NAMED_QUALIFIER)), get(named(AMAZON_NAMED_QUALIFIER))
        )
    }
}

private fun provideAmazonAuthenticatorInterceptor(): AmazonAuthenticatorInterceptor {
    return AmazonAuthenticatorInterceptor()
}

private fun provideAmazonRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient).baseUrl(AmazonApiConst.API_BASE_URL).build()
}

private fun provideGson(): Gson {
    return GsonBuilder().apply {
        registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeJsonDeserializer())
    }.create()
}

private fun provideAmazonOkHttpBuilder(
    amazonAuthenticatorInterceptor: AmazonAuthenticatorInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(amazonAuthenticatorInterceptor)
    }
}

const val AMAZON_NAMED_QUALIFIER = "amazon"