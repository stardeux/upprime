package com.stardeux.upprime.tmdb.find.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.repository.api.TmdbFindApi
import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbFindModule = module {
    single { provideFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    single { provideFindRepository(get()) }
    single { provideFindMovieUseCase(get()) }
    single { provideFindSeriesUseCase(get()) }
}

private fun provideFindApi(retrofit: Retrofit): TmdbFindApi {
    return retrofit.create(TmdbFindApi::class.java)
}

private fun provideFindRepository(tmdbFindApi: TmdbFindApi): FindMediaRepository {
    return FindMediaRepository(tmdbFindApi)
}

private fun provideFindMovieUseCase(tmdbFindRepository: FindMediaRepository): FindMovieUseCase {
    return FindMovieUseCase(tmdbFindRepository)
}

private fun provideFindSeriesUseCase(tmdbFindRepository: FindMediaRepository): FindSeriesUseCase {
    return FindSeriesUseCase(tmdbFindRepository)
}
