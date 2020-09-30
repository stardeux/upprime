package com.stardeux.upprime.media.common.repository.model.mapper

import android.net.Uri
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.api.MediaPageResponse
import com.stardeux.upprime.media.common.repository.api.MediaResponse
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaEntity
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity

class ShortMediaMapper {

    fun mapToMediaItem(mediaResponse: MediaResponse): ShortMedia? {
        return if (mediaResponse.type != null && mediaResponse.dateAdded != null) {
            with(mediaResponse) {
                ShortMedia(
                    title = title,
                    amazonId = "TODO",
                    imdbId = requireNotNull(imdbId),
                    dateAdded = dateAdded!!.toLocalDate(),
                    type = mapToMediaType(requireNotNull(type)),
                    amazonWebUrl = Uri.parse(requireNotNull(amazonWebUrl))
                )
            }
        } else {
            null
        }
    }

    fun mapToMediaPage(mediaPageResponse: MediaPageResponse): MediaPage {
        return MediaPage(
            count = requireNotNull(mediaPageResponse.count),
            shortMedia = requireNotNull(mediaPageResponse.results).mapNotNull(::mapToMediaItem)

        )
    }

    fun mapToMediaItem(latestMediaEntity: LatestMediaEntity): ShortMedia {
        return with(latestMediaEntity) {
            ShortMedia(
                title = title,
                amazonId = "TODO",
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type,
                amazonWebUrl = Uri.parse(amazonWebUrl)
            )
        }
    }

    fun mapLatestToMediaPage(latestMediaEntities: List<LatestMediaEntity>): MediaPage {
        return MediaPage(
            count = Integer.MAX_VALUE, shortMedia = latestMediaEntities.map(::mapToMediaItem)
        )
    }

    fun mapShortMediaToLatestMediaEntity(shortMedia: ShortMedia): LatestMediaEntity {
        return with(shortMedia) {
            LatestMediaEntity(
                id = 0,
                amazonWebUrl = amazonId,
                title = title,
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type
            )
        }
    }


    fun mapToMediaItem(expiredMediaEntity: ExpiredMediaEntity): ShortMedia {
        return with(expiredMediaEntity) {
            ShortMedia(
                title = title,
                amazonId = amazonWebUrl,
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type,
                amazonWebUrl = Uri.parse(amazonWebUrl)
            )
        }
    }

    fun mapExpiredToMediaPage(expiredMediaEntities: List<ExpiredMediaEntity>): MediaPage {
        return MediaPage(
            count = Integer.MAX_VALUE, shortMedia = expiredMediaEntities.map(::mapToMediaItem)
        )
    }

    fun mapShortMediaToExpiredMediaEntity(shortMedia: ShortMedia): ExpiredMediaEntity {
        return with(shortMedia) {
            ExpiredMediaEntity(
                id = 0,
                amazonWebUrl = amazonId,
                title = title,
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type
            )
        }
    }
}