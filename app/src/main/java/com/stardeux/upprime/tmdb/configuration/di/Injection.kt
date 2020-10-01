package com.stardeux.upprime.tmdb.configuration.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.common.mapper.TmdbMediaRequestMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.configuration.repository.TmdbConfigurationRepository
import com.stardeux.upprime.tmdb.configuration.repository.api.TmdbConfigurationApi
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val tmdbConfigurationModule = module {
    factory { provideTmdbConfigurationApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideTmdbConfigurationRepository(get()) }
    single { provideGetTmdbConfigurationUseCase(get()) }    //single to keep configuration cache
    factory { providePosterMapper(get()) }

    factory { provideImdbMediaRequestMapper() }
}

private fun provideImdbMediaRequestMapper(): TmdbMediaRequestMapper {
    return TmdbMediaRequestMapper()
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

private fun providePosterMapper(getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase): PosterMapper {
    return PosterMapper(getTmdbConfigurationUseCase)
}