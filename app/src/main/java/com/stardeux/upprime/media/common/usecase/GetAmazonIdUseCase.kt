package com.stardeux.upprime.media.common.usecase

import android.net.Uri
import com.stardeux.upprime.core.model.AmazonId

class GetAmazonIdUseCase{

    fun fromAmazonWebUrl(amazonWebUrl: Uri): AmazonId {
        return requireNotNull(amazonWebUrl.lastPathSegment)
    }
}