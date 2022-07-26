package com.stardeux.upprime.tmdb.video.repository

import com.stardeux.upprime.tmdb.video.repository.api.model.TmdbVideoContainerResponse
import com.stardeux.upprime.tmdb.video.repository.api.model.TmdbVideoResponse
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
                key = key,
                name = name,
                site = site,
                size = size,
                type = type
            )
        }
    }

}