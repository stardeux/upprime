package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
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
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbModule = module {
    single { provideTmdbMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideMovieRepository(get()) }
    single { provideGetMovieDetailsUseCase(get()) }

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
    single { provideGetSeriesDetailsUseCase(get()) }

    single { provideGetImdbSeriesDetailsUseCase(get(), get(), get()) }
    single { provideGetImdbMovieDetailsUseCase(get(), get()) }
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

private fun provideGetMovieDetailsUseCase(
    movieRepository: MovieRepository
): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(movieRepository)
}


private fun provideSeriesApi(retrofit: Retrofit): TmdbSeriesApi {
    return retrofit.create(TmdbSeriesApi::class.java)
}

private fun provideSeriesRepository(tmdbSeriesApi: TmdbSeriesApi): SeriesRepository {
    return SeriesRepository(tmdbSeriesApi)
}

private fun provideGetSeriesDetailsUseCase(
    seriesRepository: SeriesRepository
): GetSeriesDetailsUseCase {
    return GetSeriesDetailsUseCase(seriesRepository)
}

private fun provideGetImdbSeriesDetailsUseCase(
    findSeriesUseCase: FindSeriesUseCase,
    searchSeriesUseCase: SearchSeriesUseCase,
    getSeriesDetailsUseCase: GetSeriesDetailsUseCase
): GetImdbSeriesDetailsUseCase {
    return GetImdbSeriesDetailsUseCase(
        findSeriesUseCase,
        searchSeriesUseCase,
        getSeriesDetailsUseCase
    )
}

private fun provideGetImdbMovieDetailsUseCase(
    getMovieDetailsUseCase: GetMovieDetailsUseCase, findMovieUseCase: FindMovieUseCase
): GetImdbMovieDetailsUseCase {
    return GetImdbMovieDetailsUseCase(getMovieDetailsUseCase, findMovieUseCase)
}