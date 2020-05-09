package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.repository.api.TmdbFindApi
import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.GetMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.usecase.GetSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbModule = module {
    single { provideTmdbMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideMovieRepository(get()) }
    single { provideGetMovieDetailsUseCase(get(), get()) }

    single { provideTmdbFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideFindMediaRepository(get()) }
    single { provideFindMovieUseCase(get()) }
    single { provideFindSeriesUseCase(get()) }

    single { provideSeriesApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideSeriesRepository(get()) }
    single { provideGetSeriesDetailsUseCase(get(), get()) }
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

private fun provideGetMovieDetailsUseCase(
    movieRepository: MovieRepository, findMovieUseCase: FindMovieUseCase
): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(movieRepository, findMovieUseCase)
}


private fun provideSeriesApi(retrofit: Retrofit): TmdbSeriesApi {
    return retrofit.create(TmdbSeriesApi::class.java)
}

private fun provideSeriesRepository(tmdbSeriesApi: TmdbSeriesApi): SeriesRepository {
    return SeriesRepository(tmdbSeriesApi)
}

private fun provideGetSeriesDetailsUseCase(
    seriesRepository: SeriesRepository, findSeriesUseCase: FindSeriesUseCase
): GetSeriesDetailsUseCase {
    return GetSeriesDetailsUseCase(seriesRepository, findSeriesUseCase)
}