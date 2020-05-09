package com.stardeux.upprime.tmdb.movie.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.repository.TmdbMovieRepository
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.tmdb.movie.usecase.GetMovieDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbMovieModule = module {
    single { provideMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideMovieRepository(get()) }
    single { provideGetMovieDetailsUseCase(get()) }
}

private fun provideMovieApi(retrofit: Retrofit): TmdbMovieApi {
    return retrofit.create(TmdbMovieApi::class.java)
}

private fun provideMovieRepository(tmdbMovieApi: TmdbMovieApi): TmdbMovieRepository {
    return TmdbMovieRepository(tmdbMovieApi)
}

private fun provideGetMovieDetailsUseCase(tmdbMovieRepository: TmdbMovieRepository): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(tmdbMovieRepository)
}
