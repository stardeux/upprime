package com.stardeux.upprime.tmdb.video.ui.model

import com.stardeux.upprime.tmdb.video.usecase.MediaVideo

class MediaVideoMapper {

    fun mapToMediaVideoUi(video: MediaVideo, onMediaVideoClicked: (MediaVideoUi) -> Unit): MediaVideoUi {
        return with(video) {
            MediaVideoUi(
                key = key,
                site = site,
                type = type,
                thumbnailUrl = key?.let { getThumbnailUrl(it) },
                videoUrl = key?.let { getVideoUrl(it) },
                onMediaVideoClicked = onMediaVideoClicked
            )
        }
    }


    private fun getThumbnailUrl(videoKey: String): String {
        return String.format(THUMBNAIL_URL, videoKey)
    }

    private fun getVideoUrl(videoKey: String): String {
        return String.format(VIDEO_URL, videoKey)
    }

    companion object {
        private const val THUMBNAIL_URL = "https://i.ytimg.com/vi/%s/hqdefault.jpg"
        private const val VIDEO_URL = "http://www.youtube.com/watch?v=%s"
    }
}