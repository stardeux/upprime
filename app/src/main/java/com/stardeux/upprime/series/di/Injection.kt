package com.stardeux.upprime.series.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.series.repository.SeriesRepository
import com.stardeux.upprime.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.series.usecase.GetSeriesDetailsUseCase
import com.stardeux.upprime.series.usecase.GetUnconfiguredSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val seriesModule = module {
    factory { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSeriesRepository(get()) }
    factory { provideGetUnconfiguredSeriesDetailsUseCase(get()) }
    factory { provideGetSeriesDetailUseCase(get(), get()) }
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