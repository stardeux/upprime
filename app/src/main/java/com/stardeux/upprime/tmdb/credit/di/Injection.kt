package com.stardeux.upprime.tmdb.credit.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditRepository
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditMapper
import com.stardeux.upprime.tmdb.credit.repository.api.TmdbCreditApi
import com.stardeux.upprime.tmdb.credit.usecase.MovieCreditUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val creditModule = module {
    factory { provideTmdbCreditApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideMediaCreditMapper(get()) }
    factory { provideMediaCreditRepository(get(), get()) }
}


private fun provideTmdbCreditApi(retrofit: Retrofit): TmdbCreditApi {
    return retrofit.create(TmdbCreditApi::class.java)
}

private fun provideMediaCreditMapper(posterMapper: PosterMapper): MediaCreditMapper {
    return MediaCreditMapper(posterMapper)
}

private fun provideMediaCreditRepository(
    creditApi: TmdbCreditApi,
    mediaCreditMapper: MediaCreditMapper
): MediaCreditRepository {
    return MediaCreditRepository(creditApi, mediaCreditMapper)
}

private fun provideMediaCreditUseCase(mediaCreditRepository: MediaCreditRepository): MovieCreditUseCase {
    return MovieCreditUseCase(mediaCreditRepository)
}