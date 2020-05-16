package com.stardeux.upprime.series.di

import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.series.repository.SeriesRepository
import com.stardeux.upprime.series.repository.api.SeriesRemoteDataSource
import com.stardeux.upprime.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.series.repository.database.SeriesDao
import com.stardeux.upprime.series.repository.database.SeriesLocalDataSource
import com.stardeux.upprime.series.usecase.GetSeriesDetailsUseCase
import com.stardeux.upprime.series.usecase.GetUnconfiguredSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val seriesModule = module {
    factory { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSeriesRepository(get(), get()) }
    factory { provideGetUnconfiguredSeriesDetailsUseCase(get()) }
    factory { provideGetSeriesDetailUseCase(get(), get()) }
    factory { provideSeriesDao(get()) }
    factory { provideSeriesLocalDataSource(get()) }
    factory { provideSeriesRemoteDataSource(get()) }
}

private fun provideSeriesRemoteDataSource(tmdbSeriesApi: TmdbSeriesApi): SeriesRemoteDataSource {
    return SeriesRemoteDataSource(tmdbSeriesApi)
}

private fun provideSeriesLocalDataSource(seriesDao: SeriesDao): SeriesLocalDataSource {
    return SeriesLocalDataSource(seriesDao)
}

private fun provideSeriesDao(upPrimeDatabase: UpPrimeDatabase): SeriesDao {
    return upPrimeDatabase.seriesDetailsDao()
}

private fun provideSeriesApi(retrofit: Retrofit): TmdbSeriesApi {
    return retrofit.create(TmdbSeriesApi::class.java)
}

private fun provideSeriesRepository(
    seriesLocalDataSource: SeriesLocalDataSource,
    seriesRemoteDataSource: SeriesRemoteDataSource
): SeriesRepository {
    return SeriesRepository(seriesLocalDataSource, seriesRemoteDataSource)
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