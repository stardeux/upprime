package com.stardeux.upprime.network.di

import android.content.Context
import android.net.ConnectivityManager
import com.stardeux.upprime.api.ApiConst
import com.stardeux.upprime.network.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { ApiInterceptor() }
    factory { provideOkHttpBuilder(get(), get()) }
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
    apiInterceptor: ApiInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(apiInterceptor)
        addInterceptor(httpLoggingInterceptor)
    }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
}