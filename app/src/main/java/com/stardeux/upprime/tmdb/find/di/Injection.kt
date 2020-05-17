package com.stardeux.upprime.tmdb.find.di

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
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val findModule = module {
    factory { provideTmdbFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideFindMediaRepository(get()) }
    factory { provideFindMovieUseCase(get()) }
    factory { provideFindSeriesUseCase(get()) }

    factory { provideTmdbSearchApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSearchMovieRepository(get()) }
    factory { provideSearchSeriesRepository(get()) }
    factory { provideSearchMovieUseCase(get()) }
    factory { provideSearchSeriesUseCase(get()) }
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