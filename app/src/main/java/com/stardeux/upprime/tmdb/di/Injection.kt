package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.tmdb.movie.repository.TmdbMovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.GetMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.api.SeriesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbModule = module {
    single { provideMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideMovieRepository(get()) }
    single { provideGetMovieDetailsUseCase(get()) }

    single { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
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


private fun provideSeriesApi(retrofit: Retrofit): SeriesApi {
    return retrofit.create(SeriesApi::class.java)
}