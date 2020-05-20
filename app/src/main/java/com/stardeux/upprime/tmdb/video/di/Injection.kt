package com.stardeux.upprime.tmdb.video.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.video.repository.VideoRepository
import com.stardeux.upprime.tmdb.video.repository.api.TmdbVideoApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val videoModule = module {
    factory { provideVideoApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideVideoRepository(get()) }
}

private fun provideVideoApi(retrofit: Retrofit): TmdbVideoApi {
    return retrofit.create(TmdbVideoApi::class.java)
}

private fun provideVideoRepository(tmdbVideoApi: TmdbVideoApi): VideoRepository {
    return VideoRepository(tmdbVideoApi)
}