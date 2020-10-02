package com.stardeux.upprime.media.latest.ui

import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.common.ui.GetMediaItemUiUseCaseFacade
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.latest.usecase.GetLatestMediaUseCase

class LatestMediaViewModel(
    private val getLatestMediaUseCase: GetLatestMediaUseCase,
    mediaItemUiMapper: MediaItemUiMapper,
    getMediaItemUiUseCaseFacade: GetMediaItemUiUseCaseFacade,
    analyticsWrapper: AnalyticsWrapper
) : AmazonMediaViewModel(
    mediaItemUiMapper,
    getMediaItemUiUseCaseFacade,
    analyticsWrapper
) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getLatestMediaUseCase.getLatest(page)
    }
}