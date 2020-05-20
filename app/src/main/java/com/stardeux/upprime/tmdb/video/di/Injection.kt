package com.stardeux.upprime.tmdb.video.di

import com.stardeux.upprime.network.tmdb.di.TMDB_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.video.repository.VideoRepository
import com.stardeux.upprime.tmdb.video.repository.api.TmdbVideoApi
import com.stardeux.upprime.tmdb.video.repository.model.VideoMapper
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.*

val videoModule = module {
    factory { provideVideoApi(get(named(TMDB_NAMED_QUALIFIER))) }
    factory { provideVideoRepository(get(), get()) }
    factory { provideVideoMapper() }
    factory { provideVideoUseCase(get(), get()) }
}

private fun provideVideoApi(retrofit: Retrofit): TmdbVideoApi {
    return retrofit.create(TmdbVideoApi::class.java)
}

private fun provideVideoMapper(): VideoMapper {
    return VideoMapper()
}

private fun provideVideoRepository(tmdbVideoApi: TmdbVideoApi, videoMapper: VideoMapper): VideoRepository {
    return VideoRepository(tmdbVideoApi, videoMapper)
}

private fun provideVideoUseCase(videoRepository: VideoRepository, locale: Locale): VideoUseCase {
    return VideoUseCase(videoRepository, locale)
}