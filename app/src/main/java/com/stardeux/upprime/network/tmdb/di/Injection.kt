package com.stardeux.upprime.network.tmdb.di

import com.stardeux.upprime.network.tmdb.TmdbApiConst
import com.stardeux.upprime.network.tmdb.TmdbAuthenticatorInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
val tmdbNetworkModule = module {
    factory { provideTmdbAuthenticatorInterceptor() }
    single { provideTmdbOkHttpBuilder(get()) }
    single { provideTmdbRetrofit(get()) }
}

fun provideTmdbAuthenticatorInterceptor(): TmdbAuthenticatorInterceptor {
    return TmdbAuthenticatorInterceptor()
}

fun provideTmdbRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient).baseUrl(TmdbApiConst.API_BASE_URL).build()
}

fun provideTmdbOkHttpBuilder(
    TmdbAuthenticatorInterceptor: TmdbAuthenticatorInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(TmdbAuthenticatorInterceptor)
    }
}
 */