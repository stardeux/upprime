package com.stardeux.upprime.network.di

import com.stardeux.upprime.api.ApiConst
import com.stardeux.upprime.network.ApiInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideApiInterceptor() }
    single { provideOkHttpBuilder(get()) }
    single { provideRetrofit(get()) }
}

fun provideApiInterceptor(): ApiInterceptor {
    return ApiInterceptor()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(ApiConst.API_BASE_URL)
        .build()
}

fun provideOkHttpBuilder(
    apiInterceptor: ApiInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(apiInterceptor)
    }
}