package com.stardeux.upprime.tmdb.video.repository

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.video.repository.api.TmdbVideoApi
import com.stardeux.upprime.tmdb.video.usecase.Video
import com.stardeux.upprime.tmdb.video.repository.model.VideoMapper
import java.util.*

class VideoRepository(
    private val videoApi: TmdbVideoApi, private val videoMapper: VideoMapper
) {

    suspend fun getVideos(mediaType: MediaType, tmdbId: TmdbId, locale: Locale): List<Video>? {
        return when (mediaType) {
            MediaType.MOVIE -> videoApi.movieVideos(tmdbId, locale.language)
            MediaType.SERIES -> videoApi.seriesVideos(tmdbId, locale.language)
        }.let {
            videoMapper.mapToVideoList(it)
        }
    }

}