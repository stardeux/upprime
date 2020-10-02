package com.stardeux.upprime.tmdb.series.di

import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.common.mapper.TmdbMediaRequestMapper
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.repository.api.SeriesRemoteDataSource
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDao
import com.stardeux.upprime.tmdb.series.repository.database.SeriesLocalDataSource
import com.stardeux.upprime.tmdb.series.repository.model.SeriesDetailsMapper
import com.stardeux.upprime.tmdb.series.usecase.GetTmdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchSeriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val seriesModule = module {
    factory { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSeriesRepository(get(), get(), get()) }
    factory { provideGetSeriesDetailUseCase(get()) }
    factory { provideSeriesDao(get()) }
    factory { provideSeriesLocalDataSource(get()) }
    factory { provideSeriesRemoteDataSource(get()) }
    factory { provideSeriesMapper() }
    factory { provideGetImdbSeriesDetailsUseCase(get(), get(), get(), get()) }
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
    seriesRemoteDataSource: SeriesRemoteDataSource,
    seriesDetailsMapper: SeriesDetailsMapper
): SeriesRepository {
    return SeriesRepository(seriesLocalDataSource, seriesRemoteDataSource, seriesDetailsMapper)
}

private fun provideGetSeriesDetailUseCase(
    seriesRepository: SeriesRepository
): GetSeriesDetailsUseCase {
    return GetSeriesDetailsUseCase(seriesRepository)
}

private fun provideSeriesMapper(): SeriesDetailsMapper {
    return SeriesDetailsMapper()
}

private fun provideGetImdbSeriesDetailsUseCase(
    findSeriesUseCase: FindSeriesUseCase,
    searchSeriesUseCase: SearchSeriesUseCase,
    getSeriesDetailsUseCase: GetSeriesDetailsUseCase,
    tmdbMediaRequestMapper: TmdbMediaRequestMapper
): GetTmdbSeriesDetailsUseCase {
    return GetTmdbSeriesDetailsUseCase(
        findSeriesUseCase, searchSeriesUseCase, getSeriesDetailsUseCase, tmdbMediaRequestMapper
    )
}