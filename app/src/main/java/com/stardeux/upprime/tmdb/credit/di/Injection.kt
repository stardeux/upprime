package com.stardeux.upprime.tmdb.credit.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditRepository
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditMapper
import com.stardeux.upprime.tmdb.credit.repository.SeriesCreatorRepository
import com.stardeux.upprime.tmdb.credit.repository.api.TmdbCreditApi
import com.stardeux.upprime.tmdb.credit.usecase.MovieCreditUseCase
import com.stardeux.upprime.tmdb.credit.usecase.SeriesCreatorUseCase
import com.stardeux.upprime.tmdb.credit.usecase.SeriesCreditsUseCase
import com.stardeux.upprime.tmdb.series.repository.api.SeriesRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.*

val creditModule = module {
    factory { provideTmdbCreditApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideMediaCreditMapper() }
    factory { provideMediaCreditRepository(get(), get()) }
    factory { provideMovieCreditUseCase(get()) }

    factory { provideSeriesCreditUseCase(get(), get()) }
    factory { provideSeriesCreatorUseCase(get(), get()) }
    factory { provideSeriesCreatorRepository(get(), get()) }

}

private fun provideSeriesCreatorRepository(
    seriesRemoteDataSource: SeriesRemoteDataSource,
    mediaCreditMapper: MediaCreditMapper
): SeriesCreatorRepository {
    return SeriesCreatorRepository(seriesRemoteDataSource, mediaCreditMapper)
}

private fun provideSeriesCreatorUseCase(
    seriesCreatorRepository: SeriesCreatorRepository, locale: Locale
): SeriesCreatorUseCase {
    return SeriesCreatorUseCase(seriesCreatorRepository, locale)
}

private fun provideSeriesCreditUseCase(
    mediaCreditRepository: MediaCreditRepository, seriesCreatorUseCase: SeriesCreatorUseCase
): SeriesCreditsUseCase {
    return SeriesCreditsUseCase(mediaCreditRepository, seriesCreatorUseCase)
}


private fun provideTmdbCreditApi(retrofit: Retrofit): TmdbCreditApi {
    return retrofit.create(TmdbCreditApi::class.java)
}

private fun provideMediaCreditMapper(): MediaCreditMapper {
    return MediaCreditMapper()
}

private fun provideMediaCreditRepository(
    creditApi: TmdbCreditApi, mediaCreditMapper: MediaCreditMapper
): MediaCreditRepository {
    return MediaCreditRepository(creditApi, mediaCreditMapper)
}

private fun provideMovieCreditUseCase(mediaCreditRepository: MediaCreditRepository): MovieCreditUseCase {
    return MovieCreditUseCase(mediaCreditRepository)
}