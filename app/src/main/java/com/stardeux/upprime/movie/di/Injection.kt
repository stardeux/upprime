package com.stardeux.upprime.movie.di

import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.movie.repository.MovieRepository
import com.stardeux.upprime.movie.repository.api.MovieDetailsRemoteDataSource
import com.stardeux.upprime.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.movie.repository.database.MovieDetailDao
import com.stardeux.upprime.movie.repository.database.MovieDetailLocalDataSource
import com.stardeux.upprime.movie.usecase.GetMovieDetailsUseCase
import com.stardeux.upprime.movie.usecase.GetUnconfiguredMovieDetailsUseCase
import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val movieModule = module {
    factory { provideTmdbMovieApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideMovieDetailRemoteDataSource(get()) }
    factory { provideMovieDetailLocalDataSource(get()) }
    factory { provideMovieDetailDao(get()) }
    factory { provideMovieRepository(get(), get()) }
    factory { provideGetUnconfiguredMovieDetailsUseCase(get()) }
    factory { provideGetMovieDetailsUseCase(get(), get()) }
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