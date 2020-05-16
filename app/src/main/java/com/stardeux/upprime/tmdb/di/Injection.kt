package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.database.UpPrimeDatabase
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
import com.stardeux.upprime.tmdb.movie.repository.api.MovieDetailsRemoteDataSource
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailDao
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailLocalDataSource
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
    factory { provideTmdbConfigurationApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideTmdbConfigurationRepository(get()) }
    single { provideGetTmdbConfigurationUseCase(get()) }    //single to keep configuration cache

    factory { provideTmdbMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideMovieDetailRemoteDataSource(get()) }
    factory { provideMovieDetailLocalDataSource(get()) }
    factory { provideMovieDetailDao(get()) }
    factory { provideMovieRepository(get(), get()) }
    factory { provideGetUnconfiguredMovieDetailsUseCase(get()) }
    factory { provideGetMovieDetailsUseCase(get(), get()) }

    factory { provideTmdbFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideFindMediaRepository(get()) }
    factory { provideFindMovieUseCase(get()) }
    factory { provideFindSeriesUseCase(get()) }

    factory { provideTmdbSearchApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSearchMovieRepository(get()) }
    factory { provideSearchSeriesRepository(get()) }
    factory { provideSearchMovieUseCase(get()) }
    factory { provideSearchSeriesUseCase(get()) }

    factory { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSeriesRepository(get()) }
    factory { provideGetUnconfiguredSeriesDetailsUseCase(get()) }
    factory { provideGetSeriesDetailUseCase(get(), get()) }

    factory { provideGetImdbSeriesDetailsUseCase(get(), get(), get()) }
    factory { provideGetImdbMovieDetailsUseCase(get(), get(), get()) }
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

private fun provideMovieRepository(
    movieDetailLocalDataSource: MovieDetailLocalDataSource,
    movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource
): MovieRepository {
    return MovieRepository(movieDetailsRemoteDataSource, movieDetailLocalDataSource)
}

private fun provideMovieDetailRemoteDataSource(tmdbMovieApi: TmdbMovieApi): MovieDetailsRemoteDataSource {
    return MovieDetailsRemoteDataSource(tmdbMovieApi)
}

private fun provideMovieDetailLocalDataSource(movieDetailDao: MovieDetailDao): MovieDetailLocalDataSource {
    return MovieDetailLocalDataSource(movieDetailDao)
}

private fun provideMovieDetailDao(upPrimeDatabase: UpPrimeDatabase): MovieDetailDao {
    return upPrimeDatabase.movieDetailsDao()
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