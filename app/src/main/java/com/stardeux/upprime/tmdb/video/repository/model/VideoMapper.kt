package com.stardeux.upprime.tmdb.video.repository.model

import com.stardeux.upprime.tmdb.video.repository.api.TmdbVideoContainerResponse
import com.stardeux.upprime.tmdb.video.repository.api.TmdbVideoResponse
import com.stardeux.upprime.tmdb.video.usecase.MediaVideo

class VideoMapper {

    fun mapToVideoList(videoResponse: TmdbVideoContainerResponse): List<MediaVideo>? {
        return videoResponse.results?.map(::mapToVideo)
    }

    fun mapToVideo(videoResponse: TmdbVideoResponse): MediaVideo {
        return with(videoResponse) {
            MediaVideo(
                id = id,
                iso_639 = iso_639,
                thumbnailUrl = key?.let { getThumbnailUrl(it) },
                key = key,
                name = name,
                site = site,
                size = size,
                type = type
            )
        }
    }

    private fun getThumbnailUrl(videoKey: String): String {
        return String.format(THUMBNAIL_URL, videoKey)
    }

    companion object {
        private const val THUMBNAIL_URL = "https://i.ytimg.com/vi/%s/hqdefault.jpg"
    }
}