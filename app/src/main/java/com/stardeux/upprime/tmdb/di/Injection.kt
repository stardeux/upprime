package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.configuration.repository.TmdbConfigurationRepository
import com.stardeux.upprime.tmdb.configuration.repository.api.TmdbConfigurationApi
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.repository.SearchMovieRepository
import com.stardeux.upprime.tmdb.find.repository.SearchSeriesRepository
import com.stardeux.upprime.tmdb.find.repository.api.TmdbFindApi
import com.stardeux.upprime.tmdb.find.repository.api.TmdbSearchApi
import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchSeriesUseCase
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetMovieDetailsUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetUnconfiguredMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetUnconfiguredSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbModule = module {
    single { provideTmdbConfigurationApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideTmdbConfigurationRepository(get()) }
    single { provideGetTmdbConfigurationUseCase(get()) }    //SINGLE to keep configuration cache

    single { provideTmdbMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideMovieRepository(get()) }
    single { provideGetUnconfiguredMovieDetailsUseCase(get()) }
    single { provideGetMovieDetailsUseCase(get(), get()) }
    single { provideTmdbFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideFindMediaRepository(get()) }
    single { provideFindMovieUseCase(get()) }
    single { provideFindSeriesUseCase(get()) }

    single { provideTmdbSearchApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideSearchMovieRepository(get()) }
    single { provideSearchSeriesRepository(get()) }
    single { provideSearchMovieUseCase(get()) }
    single { provideSearchSeriesUseCase(get()) }

    single { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideSeriesRepository(get()) }
    single { provideGetUnconfiguredSeriesDetailsUseCase(get()) }
    single { provideGetSeriesDetailUseCase(get(), get()) }

    single { provideGetImdbSeriesDetailsUseCase(get(), get(), get()) }
    single { provideGetImdbMovieDetailsUseCase(get(), get(), get()) }
}


private fun provideTmdbConfigurationApi(retrofit: Retrofit): TmdbConfigurationApi {
    return retrofit.create(TmdbConfigurationApi::class.java)
}

private fun provideTmdbConfigurationRepository(tmdbConfigurationApi: TmdbConfigurationApi): TmdbConfigurationRepository {
    return TmdbConfigurationRepository(tmdbConfigurationApi)
}

private fun provideGetTmdbConfigurationUseCase(tmdbConfigurationRepository: TmdbConfigurationRepository): GetTmdbConfigurationUseCase {
    return GetTmdbConfigurationUseCase(tmdbConfigurationRepository)
}


private fun provideTmdbSearchApi(retrofit: Retrofit): TmdbSearchApi {
    return retrofit.create(TmdbSearchApi::class.java)
}

private fun provideSearchMovieRepository(tmdbSearchApi: TmdbSearchApi): SearchMovieRepository {
    return SearchMovieRepository(tmdbSearchApi)
}

private fun provideSearchSeriesRepository(tmdbSearchApi: TmdbSearchApi): SearchSeriesRepository {
    return SearchSeriesRepository(tmdbSearchApi)
}


private fun provideSearchMovieUseCase(searchMovieRepository: SearchMovieRepository): SearchMovieUseCase {
    return SearchMovieUseCase(searchMovieRepository)
}

private fun provideSearchSeriesUseCase(searchSeriesRepository: SearchSeriesRepository): SearchSeriesUseCase {
    return SearchSeriesUseCase(searchSeriesRepository)
}

private fun provideTmdbFindApi(retrofit: Retrofit): TmdbFindApi {
    return retrofit.create(TmdbFindApi::class.java)
}

private fun provideFindMediaRepository(tmdbFindApi: TmdbFindApi): FindMediaRepository {
    return FindMediaRepository(tmdbFindApi)
}

private fun provideFindMovieUseCase(findMediaRepository: FindMediaRepository): FindMovieUseCase {
    return FindMovieUseCase(findMediaRepository)
}

private fun provideFindSeriesUseCase(findMediaRepository: FindMediaRepository): FindSeriesUseCase {
    return FindSeriesUseCase(findMediaRepository)
}

private fun provideTmdbMovieApi(retrofit: Retrofit): TmdbMovieApi {
    return retrofit.create(TmdbMovieApi::class.java)
}

private fun provideMovieRepository(tmdbMovieApi: TmdbMovieApi): MovieRepository {
    return MovieRepository(tmdbMovieApi)
}

private fun provideGetUnconfiguredMovieDetailsUseCase(
    movieRepository: MovieRepository
): GetUnconfiguredMovieDetailsUseCase {
    return GetUnconfiguredMovieDetailsUseCase(movieRepository)
}

private fun provideGetMovieDetailsUseCase(
    getUnconfiguredMovieDetailsUseCase: GetUnconfiguredMovieDetailsUseCase,
    configurationUseCase: GetTmdbConfigurationUseCase
): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(getUnconfiguredMovieDetailsUseCase, configurationUseCase)
}

private fun provideSeriesApi(retrofit: Retrofit): TmdbSeriesApi {
    return retrofit.create(TmdbSeriesApi::class.java)
}

private fun provideSeriesRepository(tmdbSeriesApi: TmdbSeriesApi): SeriesRepository {
    return SeriesRepository(tmdbSeriesApi)
}

private fun provideGetUnconfiguredSeriesDetailsUseCase(
    seriesRepository: SeriesRepository
): GetUnconfiguredSeriesDetailsUseCase {
    return GetUnconfiguredSeriesDetailsUseCase(seriesRepository)
}

private fun provideGetSeriesDetailUseCase(
    getUnconfiguredSeriesDetailsUseCase: GetUnconfiguredSeriesDetailsUseCase,
    configurationUseCase: GetTmdbConfigurationUseCase
): GetSeriesDetailsUseCase {
    return GetSeriesDetailsUseCase(getUnconfiguredSeriesDetailsUseCase, configurationUseCase)
}

private fun provideGetImdbSeriesDetailsUseCase(
    findSeriesUseCase: FindSeriesUseCase,
    searchSeriesUseCase: SearchSeriesUseCase,
    getSeriesDetailsUseCase: GetSeriesDetailsUseCase
): GetImdbSeriesDetailsUseCase {
    return GetImdbSeriesDetailsUseCase(
        findSeriesUseCase, searchSeriesUseCase, getSeriesDetailsUseCase
    )
}

private fun provideGetImdbMovieDetailsUseCase(
    getMovieDetailsUseCase: GetMovieDetailsUseCase,
    findMovieUseCase: FindMovieUseCase,
    searchMovieUseCase: SearchMovieUseCase
): GetImdbMovieDetailsUseCase {
    return GetImdbMovieDetailsUseCase(getMovieDetailsUseCase, findMovieUseCase, searchMovieUseCase)
}