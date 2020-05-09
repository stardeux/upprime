package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.repository.api.MovieApi
import com.stardeux.upprime.tmdb.movie.repository.TmdbRepository
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

private fun provideMovieApi(retrofit: Retrofit): MovieApi {
    return retrofit.create(MovieApi::class.java)
}

private fun provideMovieRepository(movieApi: MovieApi): TmdbRepository {
    return TmdbRepository(movieApi)
}

private fun provideGetMovieDetailsUseCase(tmdbRepository: TmdbRepository): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(tmdbRepository)
}


private fun provideSeriesApi(retrofit: Retrofit): SeriesApi {
    return retrofit.create(SeriesApi::class.java)
}