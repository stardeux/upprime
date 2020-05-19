package com.stardeux.upprime.network.tmdb.di

import com.google.gson.*
import com.stardeux.upprime.network.tmdb.TmdbApiConst
import com.stardeux.upprime.network.tmdb.TmdbAuthenticatorInterceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.ParseException


val tmdbNetworkModule = module {
    factory { provideTmdbAuthenticatorInterceptor() }
    factory { provideGson() }
    factory(named(TMDB_NAMED_QUALIFIER)) { provideTmdbOkHttpBuilder(get()) }
    single(named(TMDB_NAMED_QUALIFIER)) { provideTmdbRetrofit(get((named(TMDB_NAMED_QUALIFIER))), get()) }
}

private fun provideTmdbAuthenticatorInterceptor(): TmdbAuthenticatorInterceptor {
    return TmdbAuthenticatorInterceptor()
}

private fun provideTmdbRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient).baseUrl(TmdbApiConst.API_BASE_URL).build()
}

private fun provideGson(): Gson {
    return GsonBuilder().apply {
        registerTypeAdapter(LocalDate::class.java, LocalDateJsonDeserializer())
    }.create()
}

private fun provideTmdbOkHttpBuilder(
    TmdbAuthenticatorInterceptor: TmdbAuthenticatorInterceptor
): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
        addInterceptor(TmdbAuthenticatorInterceptor)
    }
}

const val TMDB_NAMED_QUALIFIER = "tmdb"