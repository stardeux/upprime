package com.stardeux.upprime.tmdb.video.repository

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.video.repository.api.TmdbVideoApi
import com.stardeux.upprime.tmdb.video.usecase.MediaVideo
import java.util.*

class VideoRepository(
    private val videoApi: TmdbVideoApi, private val videoMapper: VideoMapper
) {

    suspend fun getVideos(mediaType: MediaType, tmdbId: TmdbId, locale: Locale): List<MediaVideo>? {
        return when (mediaType) {
            MediaType.MOVIE -> videoApi.movieVideos(tmdbId, locale.language)
            MediaType.SERIES -> videoApi.seriesVideos(tmdbId, locale.language)
        }.let {
            videoMapper.mapToVideoList(it)
        }
    }

}