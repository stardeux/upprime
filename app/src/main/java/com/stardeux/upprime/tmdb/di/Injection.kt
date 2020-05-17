package com.stardeux.upprime.tmdb.di

import com.stardeux.upprime.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.movie.usecase.GetMovieDetailsUseCase
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
import com.stardeux.upprime.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.series.usecase.GetSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbModule = module {
    factory { provideTmdbConfigurationApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideTmdbConfigurationRepository(get()) }
    single { provideGetTmdbConfigurationUseCase(get()) }    //single to keep configuration cache

    factory { provideTmdbFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideFindMediaRepository(get()) }
    factory { provideFindMovieUseCase(get()) }
    factory { provideFindSeriesUseCase(get()) }

    factory { provideTmdbSearchApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSearchMovieRepository(get()) }
    factory { provideSearchSeriesRepository(get()) }
    factory { provideSearchMovieUseCase(get()) }
    factory { provideSearchSeriesUseCase(get()) }

    factory { providePosterMapper(get()) }

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

private fun providePosterMapper(getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase): PosterMapper {
    return PosterMapper(getTmdbConfigurationUseCase)
}