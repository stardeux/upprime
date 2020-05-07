package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.repository.TmdbRepository
import com.stardeux.upprime.tmdb.repository.api.TmdbApi
import com.stardeux.upprime.tmdb.usecase.GetMovieDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbModule = module {
    single { provideTmdbApi(get((named(TMDB_NAMED_QUALIFIER)))) }
    factory { provideTmdbRepository(get()) }
    factory { provideGetMovieDetailsUseCase(get()) }
}

private fun provideTmdbApi(retrofit: Retrofit): TmdbApi {
    return retrofit.create(TmdbApi::class.java)
}

private fun provideTmdbRepository(tmdbApi: TmdbApi): TmdbRepository {
    return TmdbRepository(tmdbApi)
}

private fun provideGetMovieDetailsUseCase(tmdbRepository: TmdbRepository): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(tmdbRepository)
}