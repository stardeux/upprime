package com.stardeux.upprime.media.expired.ui

import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.common.ui.GetMediaItemUiUseCaseFacade
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase

class ExpiredMediaViewModel(
    private val getExpiredMediaUseCase: GetExpiredMediaUseCase,
    mediaItemUiMapper: MediaItemUiMapper,
    getMediaItemUiUseCaseFacade: GetMediaItemUiUseCaseFacade,
    analyticsWrapper: AnalyticsWrapper
) : AmazonMediaViewModel(
    mediaItemUiMapper,
    getMediaItemUiUseCaseFacade,
    analyticsWrapper
) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getExpiredMediaUseCase.getExpired(page)
    }
}