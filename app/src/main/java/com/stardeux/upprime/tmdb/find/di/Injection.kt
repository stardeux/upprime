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
import java.util.*

val findModule = module {
    factory { provideTmdbFindApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideFindMediaRepository(get()) }
    factory { provideFindMovieUseCase(get(), get()) }
    factory { provideFindSeriesUseCase(get(), get()) }

    factory { provideTmdbSearchApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideSearchMovieRepository(get()) }
    factory { provideSearchSeriesRepository(get()) }
    factory { provideSearchMovieUseCase(get(), get()) }
    factory { provideSearchSeriesUseCase(get(), get()) }
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


private fun provideSearchMovieUseCase(searchMovieRepository: SearchMovieRepository, locale: Locale): SearchMovieUseCase {
    return SearchMovieUseCase(searchMovieRepository, locale)
}

private fun provideSearchSeriesUseCase(searchSeriesRepository: SearchSeriesRepository, locale: Locale): SearchSeriesUseCase {
    return SearchSeriesUseCase(searchSeriesRepository, locale)
}

private fun provideTmdbFindApi(retrofit: Retrofit): TmdbFindApi {
    return retrofit.create(TmdbFindApi::class.java)
}

private fun provideFindMediaRepository(tmdbFindApi: TmdbFindApi): FindMediaRepository {
    return FindMediaRepository(tmdbFindApi)
}

private fun provideFindMovieUseCase(findMediaRepository: FindMediaRepository, locale: Locale): FindMovieUseCase {
    return FindMovieUseCase(findMediaRepository, locale)
}

private fun provideFindSeriesUseCase(findMediaRepository: FindMediaRepository, locale: Locale): FindSeriesUseCase {
    return FindSeriesUseCase(findMediaRepository, locale)
}