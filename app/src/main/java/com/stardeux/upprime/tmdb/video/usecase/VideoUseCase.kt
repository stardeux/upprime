package com.stardeux.upprime.tmdb.video.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.video.repository.VideoRepository
import java.util.*

class VideoUseCase(private val videoRepository: VideoRepository, private val locale: Locale) {

    suspend fun getVideos(mediaType: MediaType, tmdbId: TmdbId): List<Video>? {
        return videoRepository.getVideos(mediaType, tmdbId, locale)
    }
}