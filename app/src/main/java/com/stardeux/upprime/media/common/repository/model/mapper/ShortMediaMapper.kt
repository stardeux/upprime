package com.stardeux.upprime.media.common.repository.model.mapper

import android.net.Uri
import com.stardeux.upprime.media.common.repository.api.MediaPageResponse
import com.stardeux.upprime.media.common.repository.api.MediaResponse
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.usecase.GetAmazonIdUseCase
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaEntity
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity

class ShortMediaMapper(
    private val getAmazonIdUseCase: GetAmazonIdUseCase
) {

    fun mapToMediaItem(mediaResponse: MediaResponse): ShortMedia? {
        return if (mediaResponse.type != null && mediaResponse.dateAdded != null) {
            with(mediaResponse) {
                val amazonWebUrl = Uri.parse(requireNotNull(amazonWebUrl))
                ShortMedia(
                    title = title,
                    amazonId = getAmazonIdUseCase.fromAmazonWebUrl(amazonWebUrl),
                    imdbId = requireNotNull(imdbId),
                    dateAdded = dateAdded!!.toLocalDate(),
                    type = mapToMediaType(requireNotNull(type)),
                    amazonWebUrl = amazonWebUrl
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
            val amazonWebUrl = Uri.parse(amazonWebUrl)
            ShortMedia(
                title = title,
                amazonId = getAmazonIdUseCase.fromAmazonWebUrl(amazonWebUrl),
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type,
                amazonWebUrl = amazonWebUrl
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
                amazonWebUrl = amazonWebUrl.toString(),
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
                amazonWebUrl = amazonWebUrl.toString(),
                title = title,
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type
            )
        }
    }
}